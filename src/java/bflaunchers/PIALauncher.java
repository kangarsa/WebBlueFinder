package bflaunchers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import normalization.BasicNormalization;
import normalization.TranslatorBasicNormalization;
import pia.BipartiteGraphGenerator;
import pia.PIAConfigurationBuilder;
import pia.PIAConfigurationContainer;
import pia.PathFinder;
import db.DBConnector;
import db.PropertiesFileIsNotFoundException;
import utils.BlacklistCategory;
import utils.ProjectConfigurationReader;
import utils.ProjectSetup;
import wbflisteners.ObservableProcess;

public class PIALauncher extends ObservableProcess {

	public void launch(List<String> blacklist, String dbwiki, String dbwikiuser, String dbwikipass, String dbresults, String dbresultsuser, String dbresultspass, int infLimit, int maxLimit, int iterationsLimit, String fromToTable/*, String prefix*/, boolean cl) {

		try {
			System.out.println("ALGO0.0");
			DBConnector dbc = new DBConnector(dbwikiuser, dbwikipass, dbwiki, dbresultsuser, dbresultspass, dbresults);

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
			ProjectSetup ps = new ProjectSetup("dbtypes", false, "unstarred", 
					"Category:", "en", "http://dbpedia.org/resource/", 
					"http://dbpedia.org/resource/", this.readBlacklistCategoryFile("blacklist_category_default.txt"),
					false, true, null);

	        
			BipartiteGraphGenerator bgg;
		//	bgg = PIAConfigurationBuilder.getBipartiteGraphGenerator(iterations);
			//IMPORTATE VERIFICAR SI UTILIZA OTRO TIPO DE NORMALIZACION!!! Y SI TIENE QUE TRADUCIR.
		//	bgg = new BipartiteGraphGenerator(dbc,new BasicNormalization(),iterationsLimit);
		//	BlacklistCategory blacklistCategory = new BlacklistCategory("blacklist_category_default.txt");
//			List<String> bl = readBlacklistCategoryFile("blacklist_category_default.txt");
			System.out.println(blacklist);
/** TODO ver que onda con la exportacion de la carpeta config o si se debe copiar todos los archivos necesarios al proyecto grails **/			
			bgg = ps.getBipartiteGraphGenerator(dbc, iterations);
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
			System.out.println("ERROR PIA LAUNCHER EXCEPTION ClassNotFound");
			this.notifyStopped();
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
			System.out.println("ERROR PIA LAUNCHER EXCEPTION SQL");
			this.notifyStopped();
		} catch (PropertiesFileIsNotFoundException e) {
			System.out.println("ERROR PIA LAUNCHER EXCEPTION PropertiesFileIsNotFound");
			this.notifyStopped();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR PIA LAUNCHER EXCEPTION FileNotFound");
			this.notifyStopped();
		} catch (IOException e) {
			System.out.println("ERROR PIA LAUNCHER EXCEPTION IO");
			this.notifyStopped();
		} catch (Exception e) {
			System.out.println("ERROR PIA LAUNCHER EXCEPTION Generic");
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
