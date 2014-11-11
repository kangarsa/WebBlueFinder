package bflaunchers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import knn.clean.BlueFinderPathsFinder;
import knn.clean.InsufficentKException;
import knn.clean.KNN;
import utils.ProjectSetup;
import wbflisteners.ObservableProcess;
import db.DBConnector;
import db.PropertiesFileIsNotFoundException;

public class BFPathFinderLauncher extends ObservableProcess {

	/** SINGLE EXECUTION **/
	public void launch(String dbwiki, String dbwikiuser, String dbwikipass, String dbresults, String dbresultsuser, String dbresultspass, boolean save, String object, String subject, int k, int maxRecomm) throws ClassNotFoundException, SQLException, PropertiesFileIsNotFoundException {

//		if (args.length < 3) {
//			System.out.println("Expected arguments: single <bool save to DB> <from> <to> <neighbour> [<max recommendations>]");
//			System.exit(255);
//		}
        BlueFinderPathsFinder bfevaluation;
        try {
            if (k > 11) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            System.err.println("Invalid neighbour, set to default (5).");
			this.notifyStopped();
        }
        try {
            if (maxRecomm > 100000) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            System.err.println("Invalid number of recommendations, set to default (all).");
			this.notifyStopped();
        }
		DBConnector dbc = new DBConnector(dbwikiuser, dbwikipass, dbwiki, dbresultsuser, dbresultspass, dbresults);
		
		ProjectSetup ps = new ProjectSetup("dbtypes", "unstarred", 
				"Category:", "en", "http://dbpedia.org/resource/", 
				"http://dbpedia.org/resource/", this.readBlacklistCategoryFile("blacklist_category_default.txt"),
				false, true, null);
		
        bfevaluation = new BlueFinderPathsFinder(ps, dbc, new KNN(ps, dbc), k, maxRecomm);
        bfevaluation.setSaveResults(save);
        List<String> knnResults;
		try {
			knnResults = bfevaluation.getEvaluation(ps, object, subject, Integer.getInteger("-1"));

	        if (!save) {
	            System.out.printf("Evaluation for the pair: %s , %s, k=%d, maxRecomm=%d\n", object, subject, k, maxRecomm);
	            if (knnResults.isEmpty()) {
	                System.out.println("There are no recommendations.");
	            }
	            for (int i = 0; i < knnResults.size(); i++) {
	                System.out.println((i + 1) + "path: " + knnResults.get(i));
	            }
	        }
		} catch (InsufficentKException e) {
			System.err.println("Not enought values to calculate all K values (10).");
			e.printStackTrace();
		}

		this.notifyFinished();
	}

	/** COMPLETE EXECUTION **/
	public void launch(String dbwiki, String dbwikiuser, String dbwikipass, String dbresults, String dbresultsuser, String dbresultspass, boolean save, String scenarioName, int limit, int offset) throws ClassNotFoundException, SQLException, PropertiesFileIsNotFoundException {
// setear offset 0 si no se sabe
//		if (args.length < 3) {
//			System.out.println("Expected arguments: <bool save to DB> <scenario name> <limit> [<offset>]");
//			System.exit(255);
//		}

		DBConnector dbc = new DBConnector(dbwikiuser, dbwikipass, dbwiki, dbresultsuser, dbresultspass, dbresults);
		
		ProjectSetup ps = new ProjectSetup("dbtypes", "unstarred", 
				"Category:", "en", "http://dbpedia.org/resource/", 
				"http://dbpedia.org/resource/", this.readBlacklistCategoryFile("blacklist_category_default.txt"),
				false, true, null);
        KNN knn = new KNN(ps, dbc, ps.isCreateEnhancedTable());
        BlueFinderPathsFinder bfe = new BlueFinderPathsFinder(ps,dbc,knn);
        bfe.setSaveResults(save);

        try {
			bfe.getEvaluation(ps, scenarioName, 11, limit, offset);
	 		this.notifyFinished();
		} catch (ClassCastException e) {
			System.err.println("Class Cast Exception");
			e.printStackTrace();
			this.notifyStopped();
		} catch (InsufficentKException e) {
			System.err.println("K is low, may its a poor case to run.");
			e.printStackTrace();
			this.notifyStopped();
		}
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
