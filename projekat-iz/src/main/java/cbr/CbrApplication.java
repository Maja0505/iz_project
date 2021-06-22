package cbr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import connector.AttackCsvConnector;
import model.AttackCaseDescription;
import similarity.TableSimilarity;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

import java.util.Collection;

public class CbrApplication implements StandardCBRApplication {
	
	Connector _connector;  /** Connector object */
	CBRCaseBase _caseBase;  /** CaseBase object */

	NNConfig simConfig;  /** KNN configuration */

	Collection<RetrievalResult> results;
	
	public void configure() throws ExecutionException {
		_connector =  new AttackCsvConnector();
		
		_caseBase = new LinealCaseBase();  // Create a Lineal case base for in-memory organization
		
		simConfig = new NNConfig(); // KNN configuration
		simConfig.setDescriptionSimFunction(new Average());  // global similarity function = average
		
		simConfig.addMapping(new Attribute("alteredDocumentation", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("errorsInSoftware", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("suspiciousDataModifications", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("recentlyReceivedUpdates", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("recentlyUsedRemovableMedia", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("denialOfService", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("suspiciousCodeChanges", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("softwareInDevelopmentPhase", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("softwareInDeploymentPhase", AttackCaseDescription.class), new Equal());
		simConfig.addMapping(new Attribute("unauthenticatedPhysicalAccessRecently", AttackCaseDescription.class), new Equal());
		// TODO

		TableSimilarity typeTableSimilarity = new TableSimilarity(Arrays.asList(new String[]{"hardware","software","both"}));
		typeTableSimilarity.setSimilarity("hardware","software",0.0);
		typeTableSimilarity.setSimilarity("hardware","both",1.0);
		typeTableSimilarity.setSimilarity("hardware","both",1.0);

		simConfig.addMapping(new Attribute("type", AttackCaseDescription.class), typeTableSimilarity);

		TableSimilarity typicalSeverityTableSimilarity = new TableSimilarity(Arrays.asList(new String[]{"Low ","Medium ","High "}));
		typicalSeverityTableSimilarity.setSimilarity("Low ","Medium ",0.7);
		typicalSeverityTableSimilarity.setSimilarity("Low ","High ",0.2);
		typicalSeverityTableSimilarity.setSimilarity("Medium ","High ",0.7);

		simConfig.addMapping(new Attribute("typicalSeverity", AttackCaseDescription.class), typicalSeverityTableSimilarity);


		// Equal - returns 1 if both individuals are equal, otherwise returns 0
		// Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval)
		// Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case
		// EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
		// MaxString - returns a similarity value depending of the biggest substring that belong to both strings
		// EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
		// EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
		// Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
		// TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity) 
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 1);
		this.results=eval;
		returnResults();

	}

	public Collection<RetrievalResult> returnResults(){
		System.out.println("Retrieved cases:");
		for (RetrievalResult nse : this.results)
			System.out.println(nse.get_case().getDescription() + " -> " + nse.getEval());
		return this.results;
	}

	public void postCycle() throws ExecutionException {
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);
		Collection<CBRCase> cases = _caseBase.getCases();
		//for (CBRCase c: cases)
		//	System.out.println(c.getDescription());
		return _caseBase;
	}



}