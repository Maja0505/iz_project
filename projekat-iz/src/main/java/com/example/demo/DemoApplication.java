package com.example.demo;

import cbr.CbrApplication;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import dto.FuzzyInputDto;
import model.AttackCaseDescription;
import net.sourceforge.jFuzzyLogic.FIS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;

import unbbayes.*;
import unbbayes.example.TextMode;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.Debug;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @PutMapping("/fuzzy")
    public Double fuzzy(@RequestBody FuzzyInputDto fuzzyInputDto) {
        FIS fis = FIS.load("fcl/RiskOfAttack.fcl", true); // Load from 'FCL' file
        fis.setVariable("access_vector", fuzzyInputDto.getAccess_vector()); // Set inputs
        fis.setVariable("access_complexity", fuzzyInputDto.getAccess_complexity());
        fis.setVariable("authentication", fuzzyInputDto.getAuthentication());
        fis.setVariable("confidentiality", fuzzyInputDto.getConfidentiality());
        fis.setVariable("integrity", fuzzyInputDto.getIntegrity());
        fis.setVariable("availability", fuzzyInputDto.getAvailability());
        fis.evaluate(); // Evaluate

        // Show output variable
        System.out.println("Output value:" + fis.getVariable("risk").getValue());
        long risk = Math.round(fis.getVariable("risk").getValue() * 100);
        return  (double)risk/100;
    }

    @GetMapping("/prolog")
    public String prolog(@RequestParam(value = "myName", defaultValue = "World") String name) {

        JIPEngine engine = new JIPEngine();

        engine.consultFile("program.pl");
        JIPQuery query_pl = engine.openSynchronousQuery("all_attacks(L)");

        // pravila se mogu dodavati i tokom izvrsavanja (u runtime-u)
        // assertz dodaje pravilo na kraj programa (aasserta dodaje na pocetak programa), na primer:
        // engine.assertz(engine.getTermParser().parseTerm("sledbenik(X,Y) :- X is Y+1."));

        JIPTerm solution;
        while ( (solution = query_pl.nextSolution()) != null) {
            System.out.println("solution: " + solution);
            for (JIPVariable var: solution.getVariables()) {
                System.out.println(var.getName() + "=" + var.getValue());
            }
        }


        StandardCBRApplication recommender = new CbrApplication();

        try {

            recommender.configure();

            recommender.preCycle();

            CBRQuery query = new CBRQuery();
            AttackCaseDescription caseDescription = new AttackCaseDescription();

            // TODO

            query.setDescription( caseDescription );

            recommender.cycle(query);

            recommender.postCycle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("Hello %s!", name);
    }

    @GetMapping("/bayes")
    public String bayes(){
        unbbayes.prs.bn.ProbabilisticNetwork net = null;

        try {
            unbbayes.io.BaseIO io = new unbbayes.io.NetIO(); // open a .net file
            net = (unbbayes.prs.bn.ProbabilisticNetwork)io.load(new File("./data/attacksv2.net"));
        } catch (Exception e) {
            Debug.println(TextMode.class, "Error loading Bayesian Network", e);
            System.exit(1);
        }

        // prepare the algorithm to compile network
        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();

        System.out.println("\nNodes calculating before probabilities!!\n");
        // print node's prior marginal probabilities
        List<Node> nodeList = net.getNodes();
        List<Node> inputNodes = new ArrayList<Node>();
        List<Node> outputNodes = new ArrayList<Node>();

        for (Node node : nodeList) {
            if(node.getDescription().startsWith("S"))
                inputNodes.add(node);
            else
                outputNodes.add(node);
        }

        PrintAllInputNodes(inputNodes);

        InputNodeProbabilities(inputNodes);

        PrintAllInputNodes(inputNodes);

        PrintAllOutputNodes(outputNodes);

        // insert evidence (finding)
        int indexFirstNode = 0;
        ProbabilisticNode findingNode = (ProbabilisticNode)nodeList.get(indexFirstNode);
        int indexFirstState = 0;
        findingNode.addFinding(indexFirstState);

        System.out.println();

        // propagate evidence
        try {
            algorithm.propagate();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

        List<Node> sortedNodeList = SortNodeProbabilities(outputNodes);
        System.out.println("\nNodes after calculating probabilities and sorting!!\n");
        //print updated (posterior) node's marginal probabilities
        PrintAllOutputNodes(sortedNodeList);
        return "a";
    }

    private void PrintAllInputNodes(List<Node> nodeList){
        for (Node node : nodeList) {
            System.out.println(node.getName());
            for (int i = 0; i < node.getStatesSize(); i++) {
                System.out.println(node.getStateAt(i) + " : " + ((ProbabilisticNode) node).getMarginalAt(i));
            }
        }
    }

    private void PrintAllOutputNodes(List<Node> nodeList){
        for (Node node : nodeList) {
            System.out.println(node.getName());
            for (int i = 0; i < node.getStatesSize(); i++) {
                System.out.println(node.getStateAt(i) + " : " + ((ProbabilisticNode) node).getMarginalAt(i));
            }
        }
    }

    private void InputNodeProbabilities(List<Node> nodeList) {
        for (Node node : nodeList) {
            System.out.println(node.getName() + ":");

            if(node.getStatesSize() == 2){
                Scanner myInput = new Scanner(System.in);  // Create a Scanner object

                String strInput = myInput.nextLine();  // Read user input
                int intInput = Integer.parseInt(strInput);
                if (intInput != 0 && intInput != 1)
                    System.out.println("GRESKA!!! Unesite 1 ili 0!!!");
                SetNodeProbabilityWithTwoStates(node, (float) intInput);
            } else if(node.getStatesSize() == 3){
                Scanner myInput = new Scanner(System.in);  // Create a Scanner object

                String strInput = myInput.nextLine();  // Read user input
                int intInput = Integer.parseInt(strInput);
                if (intInput != 1 && intInput != 2 && intInput != 3)
                    System.out.println("GRESKA!!! Unesite 1, 2 ili 3!!!");
                SetNodeProbabilityWithThreeStates(node, intInput - 1);
            }

            /*for (int i = 0; i < node.getStatesSize(); i++) {
                System.out.println(node.getStateAt(i) + " : ");
                Scanner myInput = new Scanner(System.in);  // Create a Scanner object

                String strInput = myInput.nextLine();  // Read user input
                int intInput = Integer.parseInt(strInput);
                if (intInput != 0 && intInput != 1)
                    System.out.println("GRESKA!!! Unesite 1 ili 0!!!");
                //node.setStateAt();
                ((ProbabilisticNode) node).setMarginalAt(i, (float) intInput);
            }*/
        }
    }

    private List<Node> SortNodeProbabilities(List<Node> nodeList) {
        int index = -1;
        float maxProbability = -1;
        for(int i = 0; i < nodeList.size() - 1; i++){
            maxProbability = ((ProbabilisticNode) nodeList.get(i)).getMarginalAt(0);
            index = i;
            for(int j = i + 1; j < nodeList.size(); j++){
                if(((ProbabilisticNode) nodeList.get(j)).getMarginalAt(0) > maxProbability){
                    maxProbability = ((ProbabilisticNode) nodeList.get(j)).getMarginalAt(0);
                    index = j;
                }
            }
            nodeList = SwapNodes(nodeList, i, index);
        }
        return nodeList;
    }

    private List<Node> SwapNodes(List<Node> nodeList, int i, int j){
        Node temp = nodeList.get(i);
        nodeList.set(i, nodeList.get(j));
        nodeList.set(j, temp);
        return nodeList;
    }

    private Node SetNodeProbabilityWithTwoStates(Node node, float probability){
        ((ProbabilisticNode) node).setMarginalAt(0, probability);
        ((ProbabilisticNode) node).setMarginalAt(1, 1 - probability);
        return node;
    }
    private Node SetNodeProbabilityWithThreeStates(Node node, int index){
        for (int i = 0; i < node.getStatesSize(); i++) {
            if(i == index)
                ((ProbabilisticNode) node).setMarginalAt(i, 1);
            else
                ((ProbabilisticNode) node).setMarginalAt(i, 0);
        }
        return node;
    }
}
