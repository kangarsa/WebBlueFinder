package bflaunchers;

import java.sql.SQLException;

import knn.clean.BlueFinderEvaluation;
import knn.clean.KNN;
import utils.ProjectConfiguration;
import wbflisteners.ObservableProcess;

public class BFEvaluationLauncher extends ObservableProcess {

	public void launch(String scName, int proportionOfExperiment) throws ClassNotFoundException, SQLException {
		/**
		if (!(args.length == 2)){
			System.out.println("Help: You have to indicate <scenarioName> <proportionOfExperiment>");
			System.out.println("The evaluation use k from 1 to 10");
			System.out.println("Results are in generalStatistics and particularStatistics");
			System.exit(0);
		}
		**/
		String scenarioName;
		int proportion;
		try {
			scenarioName = scName;
			proportion = proportionOfExperiment;
		} catch (ArrayIndexOutOfBoundsException ex) {
			scenarioName = "sc1Evaluation";
			proportion = 3;
		}
		KNN knn = new KNN(ProjectConfiguration.enhanceTable());
		BlueFinderEvaluation bfe = new BlueFinderEvaluation(knn);
	    
		bfe.runCompleteEvaluation(proportion, 11, scenarioName);
		System.out.println("FINALIZED!!!");
		System.exit(0);
	}
}
