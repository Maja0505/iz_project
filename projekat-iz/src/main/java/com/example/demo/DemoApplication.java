package com.example.demo;

import cbr.CbrApplication;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import dto.AttackDTO;
import dto.AttackSymptomsDTO;
import dto.FuzzyInputDto;
import model.AttackCaseDescription;
import net.sourceforge.jFuzzyLogic.FIS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class DemoApplication {
    Map<String, JIPQuery> map1 = new HashMap<String,JIPQuery>();
    public static AttackDTO attackDTO;



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

    }
