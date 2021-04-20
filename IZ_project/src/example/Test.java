package example;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

public class Test {

	public static void main(String[] args) {
		JIPEngine engine = new JIPEngine();
		
		engine.consultFile("program.pl");
		JIPQuery query = engine.openSynchronousQuery("prethodnik(3,X)");
		
		// pravila se mogu dodavati i tokom izvrsavanja (u runtime-u)
		// assertz dodaje pravilo na kraj programa (aasserta dodaje na pocetak programa), na primer:
		// engine.assertz(engine.getTermParser().parseTerm("sledbenik(X,Y) :- X is Y+1."));

		JIPTerm solution;
		while ( (solution = query.nextSolution()) != null) {
			System.out.println("solution: " + solution);
			for (JIPVariable var: solution.getVariables()) {
				System.out.println(var.getName() + "=" + var.getValue());
			}
		}

	}

}
