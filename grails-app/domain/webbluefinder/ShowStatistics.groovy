package webbluefinder

import java.sql.Connection;
import db.WikipediaConnector;
import groovy.sql.Sql

class ShowStatistics {
	
	/**
	 * Este conjunto de variables (connectTo, properties), junto con el 
	método defineProperties, corresponden a testeos y deberían eliminarse
	eventualmente
	**/
	
	def connectTo = "jdbc:mysql://192.168.10.84/piaImportAux_2"
	java.util.Properties properties = new java.util.Properties()
	
    static constraints = {
    }
	
	def defineProperties() {	
		properties.put("user", "giuliano")
		properties.put("password", "123456")
		
	}
	
	def ArrayList<?> fetchPathQueries() {
		//Connection con = WikipediaConnector.getResultsConnection();
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		defineProperties()
		Connection con = driver.connect(connectTo, properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select * from V_Normalized"
		return result	
	}
	def ArrayList<?> fetchConnectedPairs() {
		//Connection con = WikipediaConnector.getResultsConnection();
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		defineProperties()	
		Connection con = driver.connect(connectTo, properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select id, CONVERT(page using utf8) as Page from U_page"
		return result
	}
	
	def ArrayList<?> fetchNotConnectedPairs() {
		//Connection con = WikipediaConnector.getResultsConnection();
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		defineProperties()
		Connection con = driver.connect(connectTo, properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select * from NFPC"
		return result
	}
	
	def ArrayList<?> fetchPQRelevance() {
		//Connection con = WikipediaConnector.getResultsConnection();
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		defineProperties()
		Connection con = driver.connect(connectTo, properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select count(v_to) as cant,vn.path from UxV inner join V_Normalized as vn on UxV.v_to=vn.id group by v_to order by cant desc limit 50"
		return result
		
	}
	
	def ArrayList<?> pqJoinCp() {
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		defineProperties()
		Connection con = driver.connect(connectTo, properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select vn.id as id_PQ,vn.path,up.id as id_CP,up.page from V_Normalized vn inner join UxV on vn.id=UxV.v_to inner join U_page up on UxV.u_from=up.id limit 10;"
		return result
		
	}
	
	def ArrayList<?> fetchConnectedPairsOfPQ(int id) {
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		defineProperties()
		Connection con = driver.connect(connectTo, properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select CONVERT(up.page using utf8) as Page from U_page up inner join UxV on up.id=UxV.u_from inner join V_Normalized vn on vn.id=UxV.v_to where vn.id="+id;
		return result
		
	}
	
	def ArrayList<?> fetchPQWhoConnects(int id) {
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		defineProperties()
		Connection con = driver.connect(connectTo, properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select vn.path from U_page up inner join UxV on up.id=UxV.u_from inner join V_Normalized vn on vn.id=UxV.v_to where up.id="+id;
		return result
		
	}
}
