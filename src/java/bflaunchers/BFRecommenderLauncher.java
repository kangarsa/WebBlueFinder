package bflaunchers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.utils.ResultsDbInterface;
import knn.clean.BlueFinderRecommender;
import knn.clean.KNN;
import utils.ProjectSetup;
import wbflisteners.ObservableProcess;

public class BFRecommenderLauncher extends ObservableProcess {
	
	public void launch(String from, String to, int neighbour, int maxRecommendations, String db) throws ClassNotFoundException, SQLException {
		/**
		if (args.length < 3) {
			System.out.println("Expected arguments: <from> <to> <neighbour> [<max recommendations>]");
			System.exit(255);
		}**/				
		String subject = from;
		String object = to;
		int k = 5;

		try {
			k = neighbour;
			if (k > 11) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {			
			System.err.println("Invalid neighbour, set to default (5).");
			k = 5;
		}

		int maxRecomm = 100000;
		try {
			maxRecomm = maxRecommendations;
		} catch (NumberFormatException ex) {
			System.err.println("Invalid number of recommendations, set to default (all).");
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.err.println("Number of recommendations was not provided, set to default (all).");
		}
		Connection conn = DatabaseConnector.getConnection("root","root");
		ResultsDbInterface rdi = new ResultsDbInterface(conn);
		
		BlueFinderRecommender bfevaluation = new BlueFinderRecommender(new KNN(new ProjectSetup()), k, maxRecomm,rdi);

		System.out.println("En BFRLauncher 5");
		List<String> knnResults = bfevaluation.getEvaluation(object,  subject);

		System.out.println("from: "+from);
		System.out.println("to: "+to);
		System.out.println("nei: "+neighbour);
		System.out.println("max: "+maxRecommendations);
		System.out.println("db: "+db);
		System.out.println("object: "+object);
		System.out.println("subject: "+subject);
		
		System.out.printf("Evaluation for the pair: %s , %s, k=%d, maxRecomm=%d\n", object, subject, k, maxRecomm);
		if (knnResults.size() == 0) {
			System.out.println("There are no recommendations.");
		}
		for (int i = 0; i < knnResults.size(); i++) {
			System.out.println((i + 1) + "path: " + knnResults.get(i));
		}

		System.out.println("En BFRLauncher 6");
        this.notifyFinished();
	}
}
