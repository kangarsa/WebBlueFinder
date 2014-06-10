package bflaunchers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection conn;
	
	// Connection con = DriverManager.getConnection("jdbc:mysql://"+WikipediaConnector.HOST+"/"+WikipediaConnector.SCHEMA+"", WikipediaConnector.USER, Wiki$
	// conn = DriverManager.getConnection("jdbc:mysql://localhost/"+WikipediaConnector.SCHEMA+"?user=root&password=root&useUnicode=true&characterEncoding=utf8");
	// conn = DriverManager.getConnection("jdbc:mysql://localhost/"+WikipediaConnector.SCHEMA+"?user=root&password=root");
	// conn = DriverManager.getConnection("jdbc:mysql://localhost/"+WikipediaConnector.SCHEMA+"?user=root&password=root");
    
    public static Connection getConnection(String u, String p) throws ClassNotFoundException, SQLException{
        if (conn == null || conn.isClosed()) {
        	Class.forName("com.mysql.jdbc.Driver");
       		conn = DriverManager.getConnection("jdbc:mysql://localhost?user="+u+"&password="+p+"&useUnicode=true&characterEncoding=utf8");
        }
        return conn;
    }
    
}
