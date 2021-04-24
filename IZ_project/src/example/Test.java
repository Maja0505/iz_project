package example;

import cbr.CbrApplication;
import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import model.AttackCaseDescription;
import org.apache.logging.log4j.LogManager;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;

import java.util.logging.Logger;

public class Test {

	public static void main(String[] args) {

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
	}

}
