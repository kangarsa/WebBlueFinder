package bflaunchers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import normalization.BasicNormalization;
import pia.BipartiteGraphGenerator;
import pia.PIAConfigurationBuilder;
import db.DBConnector;
import db.PropertiesFileIsNotFoundException;
import utils.ProjectConfigurationReader;
import wbflisteners.ObservableProcess;

public class PIALauncher extends ObservableProcess {

	public void launch(String db, String dbuser, String dbpass, int infLimit, int maxLimit, int iterationsLimit, String fromToTable/*, String prefix*/, boolean cl) {

		try {
			System.out.println("ALGO0.0");
			DBConnector dbc = new DBConnector(dbuser, dbpass, db, dbuser, dbpass, db);

			System.out.println("ALGO0.1");
			Connection conReserarch;
			conReserarch = dbc.getResultsConnection();
			System.out.println("ALGO1.1");
			Statement st = conReserarch.createStatement();
			int counter = 0;
			/**
        if (args.length < 4 || args[0].equalsIgnoreCase("help")) {
            System.out.println("Usage: <inf_limit> <max_limit> <iterations_limit> <from_to_table> [<dbpedia prefix>] [clean]");
            System.out.println("Where:");
            System.out.println("\t\t<inf_limit> is a number which represents the min row in from_to_table\n\t\t<max_limit> is a number\n\t\t <iterations_limit> is a number\n\t\t<from_to_table> name of the sources table.");
            System.out.println("dbpedia prefix is the prefix to delete");
            System.out.println("write clean as 6 parameter to clean the index results");
            return;
        }
			 **/        
			String params = "";

			System.out.println("ALGO1.2");
			long inf_limit = infLimit;
			params += inf_limit + " ";
			long max_limit = maxLimit;
			params += max_limit + " ";
			int iterations = iterationsLimit;
			params += iterations + " ";
			String from_to_table = fromToTable;

/** TODO Recibe dbpediaPrefix por parametro **/
			//String dbpediaPrefix = ProjectConfigurationReader.dbpediaPrefix();
			String dbpediaPrefix = "http://dbpedia.org/resource/";

			System.out.println("ALGO1.3");

			String clean = "tidy";
			if(cl){
				System.out.println("Clean = "+ clean);
				params += "clean ";
			}
			params += from_to_table + " ";
			System.out.println("Params: " + params + "\n");

			long start = System.nanoTime();

			BipartiteGraphGenerator bgg;
		//	bgg = PIAConfigurationBuilder.getBipartiteGraphGenerator(iterations);
			//IMPORTATE VERIFICAR SI UTILIZA OTRO TIPO DE NORMALIZACION!!! Y SI TIENE QUE TRADUCIR.
			bgg = new BipartiteGraphGenerator(dbc,new BasicNormalization(),iterationsLimit);
			if (cl) {
				dbc.restoreResultIndex();
			}

			ResultSet resultSet = st.executeQuery("SELECT * FROM " + from_to_table + " LIMIT " + inf_limit + " , " + max_limit);
			long singleCaseElapsedMillis;
			while (resultSet.next()) {
				String to = resultSet.getString("to");
				to = URLDecoder.decode(to, "UTF-8");
				String from = resultSet.getString("from");
				from = URLDecoder.decode(from, "UTF-8");
				from = from.replace(dbpediaPrefix, "");
				to = to.replace(dbpediaPrefix, "");
				System.out.printf("Case %d: processing paths from %s to %s\n", counter, from, to);
				singleCaseElapsedMillis = System.nanoTime();
				bgg.generateBiGraph(from, to);
				System.out.printf("Elapsed time for case %d: %f seconds.\n\n", counter, 
						(double)(System.nanoTime() - singleCaseElapsedMillis) / 1000000000.0);
				counter++;
			}

			long elapsedTimeMillis = System.nanoTime() - start;

			System.out.println("Regular generated paths = " + bgg.getRegularGeneratedPaths());
			System.out.println("Elapsed time in nanoseconds " + elapsedTimeMillis);
			double seconds = (double)elapsedTimeMillis / 1000000000.0;
			System.out.println("Elapsed time in seconds " + seconds);
			System.out.println("Finished.");
			st.close();
			conReserarch.close();
			this.notifyFinished();

		} catch (ClassNotFoundException e) {
			System.out.println("ERROR DBRetriever LAUNCHER EXCEPTION ClassNotFound");
			this.notifyStopped();
		} catch (SQLException e) {
			System.out.println("ERROR DBRetriever LAUNCHER EXCEPTION SQL");
			this.notifyStopped();
		} catch (PropertiesFileIsNotFoundException e) {
			System.out.println("ERROR DBRetriever LAUNCHER EXCEPTION PropertiesFileIsNotFound");
			this.notifyStopped();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR DBRetriever LAUNCHER EXCEPTION FileNotFound");
			this.notifyStopped();
		} catch (IOException e) {
			System.out.println("ERROR DBRetriever LAUNCHER EXCEPTION IO");
			this.notifyStopped();
		}

	}

}
