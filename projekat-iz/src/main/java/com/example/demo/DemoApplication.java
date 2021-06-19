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


}
