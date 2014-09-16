package bflaunchers;

import java.sql.SQLException;
import java.util.Date;

import db.DBConnector;
import db.PropertiesFileIsNotFoundException;
import db.utils.ResultsDbInterface;
import knn.clean.BlueFinderEvaluation;
import knn.clean.KNN;
import utils.ProjectSetup;
import wbflisteners.ObservableProcess;

public class BFEvaluationLauncher extends ObservableProcess {

	public void launch(String db, String dbuser, String dbpass, String scName, int proportionOfExperiment) throws ClassNotFoundException, SQLException, PropertiesFileIsNotFoundException {
		/**
		if (!(args.length == 2)){
			System.out.println("Help: You have to indicate <scenarioName> <proportionOfExperiment>");
			System.out.println("The evaluation use k from 1 to 10");
			System.out.println("Results are in generalStatistics and particularStatistics");
			System.exit(0);
		}
		**/
		Date inicio = new Date();

		DBConnector dbc = new DBConnector(db, dbuser, dbpass, db, dbuser, dbpass);
		//Connection conn = DatabaseConnector.getConnection("root","root");
		
		ResultsDbInterface rdi = new ResultsDbInterface(dbc);
		ProjectSetup setup = new ProjectSetup();
		setup.setCreateEnhancedTable(true);
		
		System.out.println("----A1");
		String scenarioName = scName;
		int proportion = proportionOfExperiment;
		KNN knn = new KNN(dbc,setup,rdi);
		
		
		System.out.println("----A2");
		BlueFinderEvaluation bfe = new BlueFinderEvaluation(knn,rdi);

		System.out.println("----A3");
		bfe.runCompleteEvaluation(proportion, 11, scenarioName);

		System.out.println("----A4");
		Date fin = new Date();
		System.out.println("BFELauncher Time: "+ (fin.getTime() - inicio.getTime()));
		this.notifyFinished();
	}
}
