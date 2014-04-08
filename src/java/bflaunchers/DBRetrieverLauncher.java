package bflaunchers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;

import edu.unlp.info.lifia.DBRetriever.QueryRetriever;

public class DBRetrieverLauncher extends ObservableProcess {

	public void launch(String base, String query, String user, String pass, String table) throws MalformedQueryException, RepositoryException, QueryEvaluationException, ClassNotFoundException, SQLException {
        
      
       if(base.equalsIgnoreCase("localhost/dtorres")){
    	   throw new RepositoryException();
       }
       
       query = "SELECT ?from ?to WHERE { { " + query + " } } ";

           
        //String query = "select ?from, ?to where {?uni a <http://dbpedia.org/ontology/University>. ?uni <http://dbpedia.org/ontology/city> ?city }"; 

        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://"+base, user, pass);
        
        Statement createST = conexion.createStatement();
        createST.executeUpdate("CREATE TABLE IF NOT EXISTS `"+table+"` (`from` varchar(800) NOT NULL, `to` varchar(800) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8");
        createST.close();
        createST = conexion.createStatement();
        createST.executeUpdate("truncate table `"+table+"`");
        
        
//        int counter = 0;
        QueryRetriever qr = new QueryRetriever(10000, query, 10000000);
        while (qr.hasNext()) {
            TupleQueryResult queryResult = qr.getNextPage();
            while (queryResult.hasNext()) {
                BindingSet bs = queryResult.next();
                Value from = bs.getValue("from");
                Value to = bs.getValue("to");
//                Statement st = 
                conexion.createStatement();
                PreparedStatement pst = conexion.prepareStatement("INSERT INTO "+table+" (`from`, `to`) VALUES (?,?)");
                if(!(from==null || to==null)){
                pst.setString(1, from.toString());
                pst.setString(2, to.toString());
                //System.out.println(from.toString());
                //System.out.println("INSERT INTO "+table+" (`from`, `to`) VALUES ('" + from.stringValue() + "','" + to.stringValue() + "' )");
                //st.executeUpdate("INSERT INTO "+table+" (`from`, `to`) VALUES ('" + from.stringValue() + "','" + to.stringValue() + "' )");
                pst.executeUpdate();
//                counter++;
                pst.close();
                }
                else{//System.out.println("NULL EN ALGUNO DE LOS VALORES");}
                //st.close();
                //System.out.println(bs.getValue("from"));
                //System.out.println(bs.getValue("to"));
            }
            //System.out.println(counter);

	        }
	        //System.out.println("Number of Inserts: " + counter);
	    }
        this.notifyFinished();
	}
	
}
