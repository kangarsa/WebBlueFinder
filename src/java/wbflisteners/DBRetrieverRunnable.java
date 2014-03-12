package wbflisteners;

/** DEPRECATED **/

import java.sql.SQLException;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

public class DBRetrieverRunnable extends ObservableProcess implements Runnable {
	Long sceneId;
//	String query = "SELECT ?from, ?to, ?fromEng, ?toEng WHERE { ?fromEng a <http://dbpedia.org/ontology/Place>. ?toEng a <http://dbpedia.org/ontology/Person>. ?toEng <http://dbpedia.org/ontology/birthPlace> ?fromEng. ?fromEng owl:sameAs ?from.?toEng owl:sameAs ?to. FILTER regex(str(?from), \"^http://es.dbpedia.org/resource/\"). FILTER regex(str(?to), \"^http://es.dbpedia.org/resource/\"). } ORDER BY ASC(?from) ASC(?to)";
//	String queryEjemplo = "SELECT ?from, ?to WHERE { ?from a <http://dbpedia.org/ontology/Place>. ?to a <http://dbpedia.org/ontology/Person>. ?to <http://dbpedia.org/ontology/birthPlace> ?from.}"
	String query;
	String table;
	String db;
	String user;
	String pass;
	
	public DBRetrieverRunnable(Long id,String ft,String tt,String prop,String host,String base,String user,String pass) {
		super();
		this.sceneId = id;
		this.query = "SELECT ?from, ?to WHERE { ?from a <FROMTYPE>. ?to a <TOTYPE>. ?to <PROPERTY> ?from. }";
		this.query = this.query.replaceAll("FROMTYPE", ft);
		this.query = this.query.replaceAll("TOTYPE", tt);
		this.query = this.query.replaceAll("PROPERTY", prop);
		this.table = id.toString() + "_" + "results";
		this.db = host + "/" + base;
		this.user = user;
		this.pass = pass;
		System.out.println(this.query);
	}

	@Override
	public void run() {
		
		try {
			System.out.println("dbretrieber por runnear()");
			(new DBRetrieverLauncher()).launch(db, query, user, pass, table);
			this.notifyFinished();
			System.out.println("dbretrieber terminado()");
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
