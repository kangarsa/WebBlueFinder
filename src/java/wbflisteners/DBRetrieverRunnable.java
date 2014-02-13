package wbflisteners;

import java.sql.SQLException;


//import finder.DBPediaFinder;


public class DBRetrieverRunnable implements Runnable {

	  @Override
	  public void run() {
			String a = "asdf";
			
			/**
			String[] arguments = new String[] {
					"'SELECT ?from, ?to, ?fromEng, ?toEng WHERE { ?fromEng a <http://dbpedia.org/ontology/Place>. ?toEng a <http://dbpedia.org/ontology/Person>. ?toEng <http://dbpedia.org/ontology/birthPlace> ?fromEng. ?fromEng owl:sameAs ?from.?toEng owl:sameAs ?to. FILTER regex(str(?from), \"^http://es.dbpedia.org/resource/\"). FILTER regex(str(?to), \"^http://es.dbpedia.org/resource/\"). } ORDER BY ASC(?from) ASC(?to)'",
					"p01_birthPlace",
					"localhost/results",
					"results",
					"results"
					};
			DBPediaFinder.main(arguments);
			**/
	  }
} 
