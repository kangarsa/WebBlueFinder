package bflaunchers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import pia.PIAConfigurationContainer;
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


		ProjectSetup ps = new ProjectSetup("dbtypes", false, "unstarred", 
				"Category:", "en", "http://dbpedia.org/resource/", 
				"http://dbpedia.org/resource/", this.readBlacklistCategoryFile("blacklist_category_default.txt"),
				false, true, null);
		
		ResultsDbInterface rdi = new ResultsDbInterface(ps,dbc);
		ps.setCreateEnhancedTable(true);
		
		System.out.println("----A1");
		String scenarioName = scName;
		int proportion = proportionOfExperiment;
		KNN knn = new KNN(ps,dbc,rdi);
		
		
		System.out.println("----A2");
		BlueFinderEvaluation bfe = new BlueFinderEvaluation(ps, dbc, knn, rdi);

		System.out.println("----A3");
		bfe.runCompleteEvaluation(ps, proportion, 11, scenarioName);

		System.out.println("----A4");
		Date fin = new Date();
		System.out.println("BFELauncher Time: "+ (fin.getTime() - inicio.getTime()));
		this.notifyFinished();
	}

	private List<String> readBlacklistCategoryFile(String fileName){

        List<String> list = new ArrayList<String>();

        try {
	        InputStream blackListIS = PIALauncher.class.getClassLoader().getResourceAsStream(fileName);
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(blackListIS));
	        String line;
				while ((line = bufferedReader.readLine()) != null) {
					list.add(line);
				}
	        bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("TODO MAL, no hay archivo");
			e.printStackTrace();
		}
        return Collections.unmodifiableList(list);
	}
}
