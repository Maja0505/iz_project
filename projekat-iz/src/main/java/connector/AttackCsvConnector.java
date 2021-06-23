package connector;

import model.AttackCaseDescription;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

public class AttackCsvConnector implements Connector {
	
	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("attacks.csv")));
			if (br == null)
			{throw new Exception("Error opening file");}
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.split(";");

				CBRCase cbrCase = new CBRCase();


				AttackCaseDescription attackCaseDescription = new AttackCaseDescription();
				attackCaseDescription.setName(values[0].replaceAll("\"",""));
				attackCaseDescription.setAlteredDocumentation(Boolean.parseBoolean(values[1]));
				attackCaseDescription.setErrorsInSoftware(Boolean.parseBoolean(values[2]));
				attackCaseDescription.setSuspiciousDataModifications(Boolean.parseBoolean(values[3]));
				attackCaseDescription.setRecentlyReceivedUpdates(Boolean.parseBoolean(values[4]));
				attackCaseDescription.setRecentlyUsedRemovableMedia(Boolean.parseBoolean(values[5]));
				attackCaseDescription.setLikelihoodOfAttack(values[6]);
				attackCaseDescription.setTypicalSeverity(values[7]);
				attackCaseDescription.setDenialOfService(Boolean.parseBoolean(values[8]));
				attackCaseDescription.setSuspiciousCodeChanges(Boolean.parseBoolean(values[9]));
				attackCaseDescription.setSoftwareInDevelopmentPhase(Boolean.parseBoolean(values[10]));
				attackCaseDescription.setSoftwareInDeploymentPhase(Boolean.parseBoolean(values[11]));
				attackCaseDescription.setUnauthenticatedPhysicalAccessRecently(Boolean.parseBoolean(values[12]));
				attackCaseDescription.setType(values[13]);
				attackCaseDescription.setDescription(values[14]);
				attackCaseDescription.setMitigations(values[15]);


				cbrCase.setDescription(attackCaseDescription);
				cases.add(cbrCase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
		return null;
	}

	@Override
	public void storeCases(Collection<CBRCase> arg0) {
	}
	
	@Override
	public void close() {
	}

	@Override
	public void deleteCases(Collection<CBRCase> arg0) {
	}

	@Override
	public void initFromXMLfile(URL arg0) throws InitializingException {
	}

}