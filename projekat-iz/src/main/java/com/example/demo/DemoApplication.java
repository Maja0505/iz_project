package com.example.demo;

import cbr.CbrApplication;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import dto.AttackDTO;
import dto.AttackSymptomsDTO;
import dto.FuzzyInputDto;
import dto.NewAttackDto;
import model.Attack;
import model.AttackCaseDescription;
import net.sourceforge.jFuzzyLogic.FIS;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import java.util.*;
import java.util.stream.Collectors;

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


import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

@SpringBootApplication
@RestController
public class DemoApplication {
    Map<String, JIPQuery> map1 = new HashMap<String,JIPQuery>();
    public static AttackDTO attackDTO;
    private static final String UPDATE_URL = "http://localhost:3030/inz/update";
    private static final String QUERY_URL = "http://localhost:3030/inz/sparql";

    private static final String PREFIX = "PREFIX na: <http://www.neurologyapp.com/na#> PREFIX xsd: <http://w3.org/2001/XMLSchema#>";



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @PostMapping("/insert")
    public Boolean insert(@RequestBody NewAttackDto newAttackDto) {

        String insertString = PREFIX + " INSERT DATA {";
        insertString += " na:" + newAttackDto.getName() + "attack a na:Attack; ";

        insertString += " na:name " + "\"" + newAttackDto.getName() + "\"^^xsd:string; ";
        insertString += " na:description " + "\"" + newAttackDto.getDescription() + "\"^^xsd:string; ";
        insertString += " na:domain " + "\"" + newAttackDto.getDomain() + "\"^^xsd:string; ";
        insertString += " na:typical_severity " + "\"" + newAttackDto.getTypical_severity() + "\"^^xsd:string; ";
        insertString += " na:likelihood_of_attack " + "\"" + newAttackDto.getLikelihood_of_attack() + "\"^^xsd:string; ";
        insertString += " na:mitigations " + "\"" + newAttackDto.getMitigations() + "\"^^xsd:string; ";
        insertString += " na:weaknesses " + "\"" + newAttackDto.getWeaknesses() + "\"^^xsd:string; ";
        insertString += " na:prerequisites " + "\"" + newAttackDto.getPrerequisites() + "\"^^xsd:string . }";


        UpdateRequest updateRequest = UpdateFactory.create(insertString);
        UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, UPDATE_URL);
        updateProcessor.execute();

        return  true;
    }

    @GetMapping("/get")
    public List<NewAttackDto> getAll() {

        List<NewAttackDto> attackDtos = new ArrayList<>();
        String selectString = PREFIX + "SELECT ?name ?description ?domain ?typical_severity ?likelihood_of_attack ?mitigations ?weaknesses ?prerequisites " + "WHERE { "
                + "    ?attack a na:Attack;" + "" +
                " na:name ?name;" +
                " na:description ?description;" +
                " na:domain ?domain;" +
                " na:typical_severity ?typical_severity;" +
                " na:likelihood_of_attack ?likelihood_of_attack;" +
                " na:mitigations ?mitigations;" +
                " na:weaknesses ?weaknesses;" +
                " na:prerequisites ?prerequisites . "   + "}";
        Query query = QueryFactory.create(selectString);
        try {
            QueryExecution exec = QueryExecutionFactory.sparqlService(QUERY_URL, query);
            ResultSet results = exec.execSelect();
            ResultSetRewindable resultSetRewindable = ResultSetFactory.copyResults(results);
            exec.close();
            while (resultSetRewindable.hasNext()) {

                QuerySolution solution = resultSetRewindable.nextSolution();
                Literal name = solution.getLiteral("name");
                Literal description = solution.getLiteral("description");
                Literal domain= solution.getLiteral("domain");
                Literal typical_severity= solution.getLiteral("typical_severity");
                Literal likelihood_of_attack= solution.getLiteral("likelihood_of_attack");
                Literal mitigations= solution.getLiteral("mitigations");
                Literal weaknesses= solution.getLiteral("weaknesses");
                Literal prerequisites= solution.getLiteral("prerequisites");


                NewAttackDto attack = new NewAttackDto();
                attack.setName(name.getString());
                attack.setDescription(description.getString());
                attack.setDomain(domain.getString());
                attack.setTypical_severity(typical_severity.getString());
                attack.setLikelihood_of_attack(likelihood_of_attack.getString());
                attack.setMitigations(mitigations.getString());
                attack.setWeaknesses(weaknesses.getString());
                attack.setPrerequisites(prerequisites.getString());



                attackDtos.add(attack);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return attackDtos;
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

    @GetMapping("/findMitigationsForAttack")
    public List<String> getMitigationsForAttack(@RequestParam(value = "attackName") String attackName) {
        List<String> mitigations = new ArrayList<>();
        JIPEngine engine = new JIPEngine();

        engine.consultFile("program.pl");
        JIPQuery query_pl = engine.openSynchronousQuery("all_mitigations_for_attack('"+attackName+"',X)");

        // pravila se mogu dodavati i tokom izvrsavanja (u runtime-u)
        // assertz dodaje pravilo na kraj programa (aasserta dodaje na pocetak programa), na primer:
        // engine.assertz(engine.getTermParser().parseTerm("sledbenik(X,Y) :- X is Y+1."));

        JIPTerm solution;

            String result = query_pl.nextSolution().getVariables()[0].toString().replaceAll("'\\.'\\('", "").replaceAll(",\\[]\\)\\)\\)\\)", "")
                    .replaceAll("'", "").replaceAll(",\\[]\\)", "").replaceAll("\\)", "").replaceAll("\\.,", "\\.;");

            if(result.equals("[]")){
                   JIPQuery query_pl1 = engine.openSynchronousQuery("find_description_for_attack('"+attackName+"',X)");
                    JIPTerm solution1= query_pl1.nextSolution();
                   if(solution1!= null) {
                       System.out.println("gde ne treba");
                       String result1 = solution1.getVariables()[0].toString().replaceAll("'\\.'\\('", "").replaceAll(",\\[]\\)\\)\\)\\)", "")
                               .replaceAll("'", "").replaceAll(",\\[]\\)", "").replaceAll("\\)", "").replaceAll("\\.,", "\\.;").replaceAll("\\[]", "");
                       String[] descriptionsForAttack = result1.split(",");

                       for (int i=0;i<descriptionsForAttack.length;i++) {
                           System.out.println(descriptionsForAttack[i]);
                           JIPQuery query_pl2 = engine.openSynchronousQuery("mitigation_for_attack_symptom('" + descriptionsForAttack[i].trim() + "',X)");
                           String result2 = query_pl2.nextSolution().getVariables()[0].toString().replaceAll("'\\.'\\('", "").replaceAll(",\\[]\\)\\)\\)\\)", "")
                                   .replaceAll("'", "").replaceAll(",\\[]\\)", "").replaceAll("\\)", "").replaceAll("\\.,", "\\.;").replaceAll("\\[]", "");
                           mitigations.add(result2);
                       }
                   }
                   else{
                       JIPQuery query_pl3 = engine.openSynchronousQuery("find_type_for_attack('"+ attackName +"',X)");
                       String type = query_pl3.nextSolution().getVariables()[0].toString().replaceAll("'\\.'\\('", "").replaceAll(",\\[]\\)\\)\\)\\)", "")
                               .replaceAll("'", "").replaceAll(",\\[]\\)", "").replaceAll("\\)", "").replaceAll("\\.,", "\\.;").replaceAll("\\[]", "");

                       JIPQuery query_pl4 = engine.openSynchronousQuery("mitigation_for_attack_type('"+ type +"',X)");
                       String result4 = query_pl4.nextSolution().getVariables()[0].toString().replaceAll("'\\.'\\('", "").replaceAll(",\\[]\\)\\)\\)\\)", "")
                               .replaceAll("'", "").replaceAll(",\\[]\\)", "").replaceAll("\\)", "").replaceAll("\\.,", "\\.;").replaceAll("\\[]", "");

                       mitigations.add(result4);
                   }

            }else{
                mitigations= Arrays.stream(result.split(",")).collect(Collectors.toList());
            }



        return mitigations;
    }

    @PutMapping("/findAttackForSymptoms")
    public AttackDTO findAttackForSymptoms(@RequestBody AttackSymptomsDTO attackSymptomsDTO) {
        StandardCBRApplication recommender = new CbrApplication();

        try {

            recommender.configure();

            recommender.preCycle();

            CBRQuery query = new CBRQuery();
            AttackCaseDescription caseDescription = new AttackCaseDescription();
            caseDescription.setType(attackSymptomsDTO.getType().toLowerCase(Locale.ROOT));
            caseDescription.setUnauthenticatedPhysicalAccessRecently(attackSymptomsDTO.isUnauthenticatedPhysicalAccessRecently());
            caseDescription.setSoftwareInDeploymentPhase(attackSymptomsDTO.isSoftwareInDeploymentPhase());
            caseDescription.setSoftwareInDevelopmentPhase(attackSymptomsDTO.isSoftwareInDevelopmentPhase());
            caseDescription.setTypicalSeverity(attackSymptomsDTO.getTypicalSeverity());
            caseDescription.setSuspiciousCodeChanges(attackSymptomsDTO.isSuspiciousCodeChanges());
            caseDescription.setDenialOfService(attackSymptomsDTO.isDenialOfService());
            caseDescription.setRecentlyUsedRemovableMedia(attackSymptomsDTO.isRecentlyUsedRemovableMedia());
            caseDescription.setAlteredDocumentation(attackSymptomsDTO.isAlteredDocumentation());
            caseDescription.setErrorsInSoftware(attackSymptomsDTO.isErrorsInSoftware());
            caseDescription.setRecentlyReceivedUpdates(attackSymptomsDTO.isRecentlyReceivedUpdates());

            query.setDescription( caseDescription );

            recommender.cycle(query);
            recommender.postCycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attackDTO;
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
