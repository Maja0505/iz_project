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
import dto.*;
import model.AttackCaseDescription;
import net.sourceforge.jFuzzyLogic.FIS;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import javax.servlet.http.HttpServletResponse;
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
    private static final String UPDATE_URL = "http://localhost:3030/iz_7/update";
    private static final String QUERY_URL = "http://localhost:3030/iz_7/sparql";

    private static final String PREFIX = "PREFIX sca: <http://www.supply_chain_attack.com/sca#> PREFIX xsd: <http://w3.org/2001/XMLSchema#>";



    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @PostMapping("/add-attack")
    public ResponseEntity<Boolean> addAttack(@RequestBody NewAttackDto newAttackDto) {

        if(existAttackWithSameName(newAttackDto.getName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            String insertString = PREFIX + " INSERT DATA {"
                    + " sca:" + newAttackDto.getName() + "attack a sca:Attack; "
                    + " sca:name " + "\"" + newAttackDto.getName() + "\"^^xsd:string; "
                    + " sca:description " + "\"" + newAttackDto.getDescription() + "\"^^xsd:string; "
                    + " sca:domain " + "\"" + newAttackDto.getDomain() + "\"^^xsd:string; "
                    + " sca:typical_severity " + "\"" + newAttackDto.getTypical_severity() + "\"^^xsd:string; "
                    + " sca:likelihood_of_attack " + "\"" + newAttackDto.getLikelihood_of_attack() + "\"^^xsd:string; "
                    + " sca:mitigations " + "\"" + newAttackDto.getMitigations() + "\"^^xsd:string; "
                    + " sca:weaknesses " + "\"" + newAttackDto.getWeaknesses() + "\"^^xsd:string; "
                    + " sca:prerequisites " + "\"" + newAttackDto.getPrerequisites() + "\"^^xsd:string . }";


            UpdateRequest updateRequest = UpdateFactory.create(insertString);
            UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, UPDATE_URL);
            updateProcessor.execute();

            return  new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/get-all-attacks")
    public ResponseEntity<List<NewAttackDto>> getAllAttack() {

        List<NewAttackDto> attackDtos = new ArrayList<>();
        String selectString = PREFIX + "SELECT ?name ?description ?domain ?typical_severity ?likelihood_of_attack ?mitigations ?weaknesses ?prerequisites " + "WHERE { "
                + "    ?attack a sca:Attack;" + "" +
                " sca:name ?name;" +
                " sca:description ?description;" +
                " sca:domain ?domain;" +
                " sca:typical_severity ?typical_severity;" +
                " sca:likelihood_of_attack ?likelihood_of_attack;" +
                " sca:mitigations ?mitigations;" +
                " sca:weaknesses ?weaknesses;" +
                " sca:prerequisites ?prerequisites . "   + "}";
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
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST) ;

        }

        return new ResponseEntity<>(attackDtos,HttpStatus.OK) ;
    }

    private Boolean existAttackWithSameName(String attackName) {

        String selectString = PREFIX + "SELECT ?name " + "WHERE { "
                + " ?attack a sca:Attack;" + "" +
                " sca:name ?name . " + "}";
        Query query = QueryFactory.create(selectString);
        try {
            QueryExecution exec = QueryExecutionFactory.sparqlService(QUERY_URL, query);
            ResultSet results = exec.execSelect();
            ResultSetRewindable resultSetRewindable = ResultSetFactory.copyResults(results);
            exec.close();
            while (resultSetRewindable.hasNext()) {

                QuerySolution solution = resultSetRewindable.nextSolution();
                Literal name = solution.getLiteral("name");
                String stringName = name.toString().split("\\^\\^")[0];
                if(stringName.equals(attackName)){
                    return true;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    private Attack getAttackByName(String attackName){
        String selectString = PREFIX + "SELECT ?name ?description ?domain ?typical_severity ?likelihood_of_attack ?mitigations ?weaknesses ?prerequisites " + "WHERE { "
                + "    ?attack a sca:Attack;" + "" +
                " sca:name ?name;" +
                " sca:description ?description;" +
                " sca:domain ?domain;" +
                " sca:typical_severity ?typical_severity;" +
                " sca:likelihood_of_attack ?likelihood_of_attack;" +
                " sca:mitigations ?mitigations;" +
                " sca:weaknesses ?weaknesses;" +
                " sca:prerequisites ?prerequisites . "   + "}";
        Query query = QueryFactory.create(selectString);
        try {
            QueryExecution exec = QueryExecutionFactory.sparqlService(QUERY_URL, query);
            ResultSet results = exec.execSelect();
            ResultSetRewindable resultSetRewindable = ResultSetFactory.copyResults(results);
            exec.close();
            while (resultSetRewindable.hasNext()) {

                QuerySolution solution = resultSetRewindable.nextSolution();
                Literal name = solution.getLiteral("name");
                String stringName = name.toString().split("\\^\\^")[0];
                if(stringName.equals(attackName)){

                    Literal description = solution.getLiteral("description");
                    Literal domain= solution.getLiteral("domain");
                    Literal typical_severity= solution.getLiteral("typical_severity");
                    Literal likelihood_of_attack= solution.getLiteral("likelihood_of_attack");
                    Literal mitigations= solution.getLiteral("mitigations");
                    Literal weaknesses= solution.getLiteral("weaknesses");
                    Literal prerequisites= solution.getLiteral("prerequisites");


                    Attack attack = new Attack();
                    attack.setName(name.getString());
                    attack.setDescription(description.getString());
                    attack.setDomain(domain.getString());
                    attack.setTypical_severity(typical_severity.getString());
                    attack.setLikelihood_of_attack(likelihood_of_attack.getString());
                    attack.setMitigations(mitigations.getString());
                    attack.setWeaknesses(weaknesses.getString());
                    attack.setPrerequisites(prerequisites.getString());


                    return attack;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    @PutMapping("/update-attack")
    public ResponseEntity<Boolean> updateAttack(@RequestBody NewAttackDto newAttackDto){
        try{

            Attack attack = getAttackByName(newAttackDto.getName());
            if (attack != null){
                String deleteString = PREFIX + " DELETE DATA {"
                        + " sca:" + attack.getName() + "attack a sca:Attack; "
                        + " sca:name " + "\"" + attack.getName() + "\"^^xsd:string; "
                        + " sca:description " + "\"" + attack.getDescription() + "\"^^xsd:string; "
                        + " sca:domain " + "\"" + attack.getDomain() + "\"^^xsd:string; "
                        + " sca:typical_severity " + "\"" + attack.getTypical_severity() + "\"^^xsd:string; "
                        + " sca:likelihood_of_attack " + "\"" + attack.getLikelihood_of_attack() + "\"^^xsd:string; "
                        + " sca:mitigations " + "\"" + attack.getMitigations() + "\"^^xsd:string; "
                        + " sca:weaknesses " + "\"" + attack.getWeaknesses() + "\"^^xsd:string; "
                        + " sca:prerequisites " + "\"" + attack.getPrerequisites() + "\"^^xsd:string . }";

                String insertString = PREFIX + " INSERT DATA {"
                        + " sca:" + newAttackDto.getName() + "attack a sca:Attack; "
                        + " sca:name " + "\"" + newAttackDto.getName() + "\"^^xsd:string; "
                        + " sca:description " + "\"" + newAttackDto.getDescription() + "\"^^xsd:string; "
                        + " sca:domain " + "\"" + newAttackDto.getDomain() + "\"^^xsd:string; "
                        + " sca:typical_severity " + "\"" + newAttackDto.getTypical_severity() + "\"^^xsd:string; "
                        + " sca:likelihood_of_attack " + "\"" + newAttackDto.getLikelihood_of_attack() + "\"^^xsd:string; "
                        + " sca:mitigations " + "\"" + newAttackDto.getMitigations() + "\"^^xsd:string; "
                        + " sca:weaknesses " + "\"" + newAttackDto.getWeaknesses() + "\"^^xsd:string; "
                        + " sca:prerequisites " + "\"" + newAttackDto.getPrerequisites() + "\"^^xsd:string . }";


                UpdateRequest deleteRequest = UpdateFactory.create(deleteString);
                UpdateProcessor deleteProcessor = UpdateExecutionFactory.createRemote(deleteRequest, UPDATE_URL);
                deleteProcessor.execute();

                UpdateRequest insertRequest = UpdateFactory.create(insertString);
                UpdateProcessor insertProcessor = UpdateExecutionFactory.createRemote(insertRequest, UPDATE_URL);
                insertProcessor.execute();


                return  new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
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
        attackName=attackName.replaceAll("_"," ");
        System.out.print(attackName);
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
                       JIPTerm solution3= query_pl3.nextSolution();
                       if(solution3!=null){
                           String type = solution3.getVariables()[0].toString().replaceAll("'\\.'\\('", "").replaceAll(",\\[]\\)\\)\\)\\)", "")
                                   .replaceAll("'", "").replaceAll(",\\[]\\)", "").replaceAll("\\)", "").replaceAll("\\.,", "\\.;").replaceAll("\\[]", "");

                           JIPQuery query_pl4 = engine.openSynchronousQuery("mitigation_for_attack_type('"+ type +"',X)");
                           String result4 = query_pl4.nextSolution().getVariables()[0].toString().replaceAll("'\\.'\\('", "").replaceAll(",\\[]\\)\\)\\)\\)", "")
                                   .replaceAll("'", "").replaceAll(",\\[]\\)", "").replaceAll("\\)", "").replaceAll("\\.,", "\\.;").replaceAll("\\[]", "");

                           mitigations.add(result4);
                       }
                       else{
                           mitigations.add("No mitigations for this attack.");
                       }

                   }

            }else{
                mitigations= Arrays.stream(result.split(",")).collect(Collectors.toList());
            }

            for(String mitigation:mitigations){
                System.out.println(mitigation);
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

    @PutMapping("/bayes")
    public List<BayesOutputDTO> bayes(@RequestBody BayesSymptomsDTO bayesSymptomsDTO, HttpServletResponse response){
        System.out.println("Usaoooooo");
        unbbayes.prs.bn.ProbabilisticNetwork net = null;
        System.out.println(bayesSymptomsDTO.getSuspicious_code_changes());
        System.out.println(bayesSymptomsDTO.getCompany_size());

        List<BayesOutputDTO> ret = new ArrayList<BayesOutputDTO>();

        try {
            unbbayes.io.BaseIO io = new unbbayes.io.NetIO(); // open a .net file
            net = (unbbayes.prs.bn.ProbabilisticNetwork)io.load(new File("./data/attacksv2.net"));
        } catch (Exception e) {
            Debug.println(TextMode.class, "Error loading Bayesian Network", e);
            System.exit(1);
        }

        //net.updateEvidences();

        // prepare the algorithm to compile network
        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();



        System.out.println("\nNodes calculating before probabilities!!\n");
        // print node's prior marginal probabilities
        List<Node> nodeList = net.getNodes();
        //ProbabilisticNode a = (ProbabilisticNode) nodeList.get(0)

        System.out.println("\nNodes calculating before probabilities!!\n");
        PrintAllInputNodes(nodeList);

        LoadBayesProbabilities(nodeList, bayesSymptomsDTO);
        //InputNodeProbabilities(nodeList);

        System.out.println("\nNodes afterbayesloade!!\n");
        PrintAllInputNodes(nodeList);
        System.out.println("\nOutput nodes!!\n");
        //PrintAllOutputNodes(nodeList);

        // insert evidence (finding)
       /* int indexFirstNode = 0;
        ProbabilisticNode findingNode = (ProbabilisticNode)nodeList.get(indexFirstNode);
        int indexFirstState = 0;
        findingNode.addFinding(indexFirstState);

        System.out.println();
        */
        // propagate evidence

        try {
            net.updateEvidences();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

        try {
            algorithm.propagate();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

        System.out.println("\nMOHEEE\n");
        ArrayList<Node> newn = net.getNodes();
        PrintAllInputNodes(newn);

        List<Node> sortedNodeList = SortNodeProbabilities(nodeList);
        System.out.println("\nNodes after calculating probabilities and sorting!!\n");
        //print updated (posterior) node's marginal probabilities
        PrintAllOutputNodes(sortedNodeList);

        int retcnt = 0;
        for(int i = 0; i < sortedNodeList.size(); i++){
            if(nodeList.get(i).getDescription().startsWith("S"))
                continue;
            BayesOutputDTO temp = new BayesOutputDTO(ParseStringForOutput(sortedNodeList.get(i).getName()), ((ProbabilisticNode) sortedNodeList.get(i)).getMarginalAt(0));
            ret.add(temp);
            retcnt++;
            if(retcnt == 3)
                break;
        }
        return ret;
    }

    private void LoadBayesProbabilities(List<Node> inputNodes, BayesSymptomsDTO symptoms){
        for(Node node : inputNodes){
            if(node.getName().equals("Suspicious_code_changes")){
                System.out.println(symptoms.getSuspicious_code_changes());
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getSuspicious_code_changes());
            }else if(node.getName().equals("Suspicious_data_modifications")) {
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getSuspicious_data_modifications());
            }else if(node.getName().equals("Company_size")){
                SetNodeProbabilityWithThreeStates(node, symptoms.getCompany_size());
            }else if(node.getName().equals("Software_in_development_phase")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getSoftware_in_development_phase());
            }else if(node.getName().equals("Software_in_deployment_phase")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getSoftware_in_deployment_phase());
            }else if(node.getName().equals("Using_open_source_or_3rd_party_components")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getUsing_open_source_or_3rd_party_components());
            }else if(node.getName().equals("Unauthorized_physical_access_occured_recently")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getUnauthorized_physical_access_occured_recently());
            }else if(node.getName().equals("Recently_received_updates")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getRecently_received_updates());
            }else if(node.getName().equals("Recently_used_removable_media")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getRecently_used_removable_media());
            }else if(node.getName().equals("Unefectivness_or_errors_in_software")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getUnefectivness_or_errors_in_software());
            }else if(node.getName().equals("Denial_of_service")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getDenial_of_service());
            }else if(node.getName().equals("Altered_documentation")){
                SetNodeProbabilityWithTwoStatesD(node, symptoms.getAltered_documentation());
            }
        }
    }



    private void PrintAllInputNodes(List<Node> nodeList){
        for (Node node : nodeList) {
            if(node.getDescription().startsWith("S")) {
                System.out.println(node.getName());
                for (int i = 0; i < node.getStatesSize(); i++) {
                    System.out.println(node.getStateAt(i) + " : " + ((ProbabilisticNode) node).getMarginalAt(i));
                }
            }
        }
    }

    private void PrintAllOutputNodes(List<Node> nodeList){
        for (Node node : nodeList) {
            if(node.getDescription().startsWith("C")) {
                System.out.println(node.getName());
                for (int i = 0; i < node.getStatesSize(); i++) {
                    System.out.println(node.getStateAt(i) + " : " + ((ProbabilisticNode) node).getMarginalAt(i));
                }
            }
        }
    }

    private void InputNodeProbabilities(List<Node> nodeList) {
        for (Node node : nodeList) {
            if(node.getDescription().startsWith("S")) {
                System.out.println(node.getName() + ":");

                if (node.getStatesSize() == 2) {
                    Scanner myInput = new Scanner(System.in);  // Create a Scanner object

                    String strInput = myInput.nextLine();  // Read user input
                    int intInput = Integer.parseInt(strInput);
                    if (intInput != 0 && intInput != 1)
                        System.out.println("GRESKA!!! Unesite 1 ili 0!!!");
                    SetNodeProbabilityWithTwoStates(node, (float) intInput);
                } else if (node.getStatesSize() == 3) {
                    Scanner myInput = new Scanner(System.in);  // Create a Scanner object

                    String strInput = myInput.nextLine();  // Read user input
                    int intInput = Integer.parseInt(strInput);
                    if (intInput != 1 && intInput != 2 && intInput != 3)
                        System.out.println("GRESKA!!! Unesite 1, 2 ili 3!!!");
                    SetNodeProbabilityWithThreeStates(node, intInput - 1);
                }
            }
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
        if(probability == 1)
            ((ProbabilisticNode) node).addFinding(0);
        else
            ((ProbabilisticNode) node).addFinding(1);
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
    private Node SetNodeProbabilityWithTwoStatesD(Node node, boolean checked){
        if(checked == true) {
            ((ProbabilisticNode) node).addFinding(0);
        }else{
            ((ProbabilisticNode) node).addFinding(1);
        }
        return node;
    }
    private Node SetNodeProbabilityWithThreeStatesD(Node node, int index){
                ((ProbabilisticNode) node).addFinding(index);
        return node;
    }
    private String ParseStringForOutput(String input){
        String words[] = input.split("_");
        String output = "";
        for(String w:words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            output += first.toUpperCase() + afterfirst + " ";
        }
        return output;
    }
}
