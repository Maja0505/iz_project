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

		engine.consultFile("IZ_project/program.pl");
		JIPQuery query_pl = engine.openSynchronousQuery("is_mitigations_empty('USB Memory Attacks')");

		// pravila se mogu dodavati i tokom izvrsavanja (u runtime-u)
		// assertz dodaje pravilo na kraj programa (aasserta dodaje na pocetak programa), na primer:
		// engine.assertz(engine.getTermParser().parseTerm("sledbenik(X,Y) :- X is Y+1."));

		JIPTerm solution;
		if(query_pl.nextSolution()==null){
			System.out.println("result empty");
		}
		else {
			String result = query_pl.nextSolution().getVariables()[0].toString().replaceAll("'\\.'\\('", "").replaceAll(",\\[]\\)\\)\\)\\)", "")
					.replaceAll("'", "").replaceAll(",\\[]\\)", "").replaceAll("\\)", "").replaceAll("\\.,", "\\.;");
			System.out.println(result);
		}


		StandardCBRApplication recommender = new CbrApplication();

		try {

			recommender.configure();

			recommender.preCycle();

			CBRQuery query = new CBRQuery();
			AttackCaseDescription caseDescription = new AttackCaseDescription();
			caseDescription.setType("hardware");
			caseDescription.setUnauthenticatedPhysicalAccessRecently(false);
			caseDescription.setSoftwareInDeploymentPhase(false);
			caseDescription.setSoftwareInDevelopmentPhase(false);
			caseDescription.setTypicalSeverity("Low");
			caseDescription.setSuspiciousCodeChanges(false);
			caseDescription.setDenialOfService(false);
			caseDescription.setRecentlyUsedRemovableMedia(true);
			caseDescription.setAlteredDocumentation(false);
			caseDescription.setErrorsInSoftware(true);
			caseDescription.setRecentlyReceivedUpdates(false);


			// TODO

			query.setDescription( caseDescription );

			recommender.cycle(query);

			recommender.postCycle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
