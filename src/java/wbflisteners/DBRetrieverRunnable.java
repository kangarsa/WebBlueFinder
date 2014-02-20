package wbflisteners;

import java.sql.SQLException;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;



//import finder.DBPediaFinder;


public class DBRetrieverRunnable implements Runnable {

	  @Override
	  public void run() {
			String query = "'SELECT ?from, ?to, ?fromEng, ?toEng WHERE { ?fromEng a <http://dbpedia.org/ontology/Place>. ?toEng a <http://dbpedia.org/ontology/Person>. ?toEng <http://dbpedia.org/ontology/birthPlace> ?fromEng. ?fromEng owl:sameAs ?from.?toEng owl:sameAs ?to. FILTER regex(str(?from), \"^http://es.dbpedia.org/resource/\"). FILTER regex(str(?to), \"^http://es.dbpedia.org/resource/\"). } ORDER BY ASC(?from) ASC(?to)'";
			String table = "p01_birthPlace";
			String base = "localhost/results";
			String user = "results";
			String pass = "results";
			
			try {

				System.out.println("dbretrieber por runnear()");
				DBRetrieverLauncher.launch(base, query, user, pass, table);
				System.out.println("dbretrieber por runneado()");
			} catch (MalformedQueryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (QueryEvaluationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
	  }
   


}
