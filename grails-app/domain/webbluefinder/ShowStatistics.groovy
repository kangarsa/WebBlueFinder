package webbluefinder

import java.sql.Connection;
import db.WikipediaConnector;
import groovy.sql.Sql

class ShowStatistics {
	
	def pathQueries
	def connectedPairs
	def notConnectedPairs

    static constraints = {
    }
	
	def ArrayList<?> fetchPathQueries() {
		//Connection con = WikipediaConnector.getResultsConnection();
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		java.util.Properties properties = new java.util.Properties()
		properties.put("user", "root")
		properties.put("password", "root")
		Connection con = driver.connect("jdbc:mysql://localhost/piaImportAux_2", properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select * from V_Normalized"
		return result	
	}
	def ArrayList<?> fetchConnectedPairs() {
		//Connection con = WikipediaConnector.getResultsConnection();
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		java.util.Properties properties = new java.util.Properties()
		properties.put("user", "root")
		properties.put("password", "root")
		Connection con = driver.connect("jdbc:mysql://localhost/piaImportAux_2", properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select * from U_page"
		return result
	}
	
	def ArrayList<?> fetchNotConnectedPairs() {
		//Connection con = WikipediaConnector.getResultsConnection();
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		java.util.Properties properties = new java.util.Properties()
		properties.put("user", "root")
		properties.put("password", "root")
		Connection con = driver.connect("jdbc:mysql://localhost/piaImportAux_2", properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select * from NFPC"
		return result
	}
	
	def ArrayList<?> fetchPQRelevance() {
		//Connection con = WikipediaConnector.getResultsConnection();
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		java.util.Properties properties = new java.util.Properties()
		properties.put("user", "root")
		properties.put("password", "root")
		Connection con = driver.connect("jdbc:mysql://localhost/piaImportAux_2", properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select count(v_to) as cant,vn.path from UxV inner join V_Normalized as vn on UxV.v_to=vn.id group by v_to order by cant desc limit 50"
		return result
		
	}
	
	def ArrayList<?> pqJoinCp() {
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		java.util.Properties properties = new java.util.Properties()
		properties.put("user", "root")
		properties.put("password", "root")
		Connection con = driver.connect("jdbc:mysql://localhost/piaImportAux_2", properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select vn.id as id_PQ,vn.path,up.id as id_CP,up.page from V_Normalized vn inner join UxV on vn.id=UxV.v_to inner join U_page up on UxV.u_from=up.id limit 10;"
		return result
		
	}
}
