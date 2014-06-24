package webbluefinder

/**
 * v0.1
 * Clase del dominio que se encarga de realizar las consultas a la BBDD relacionadas a las tablas
 * utilizadas por un escenario (U_page, V_Normalized, NFPC, y UxV), para luego ser presentada
 * por el controlador que lo desee (en principio es solamente ShowStatisticsController).
 * Por el momento se utiliza una base de testeo llamada piaImportAux, que tiene la info
 * resultante de la ejecución de un PIA de un escenario. Más adelante esto se tendría
 * que realizar simplemente con la info del ID del escenario, e ir a buscar todas las tablas
 * pertinentes al mismo (asimismo expandir las estadísticas pertinentes a un escenario)
 * 
 * @author giuliano
 * 
 */

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

	/**Si acá pongo 'Sql' en lugar de 'def', Hibernate trata de mapearlo y tira error.
	 * Creo que hay una annotation para decirle que no
	 */
	def sql
	
    static constraints = {
    }
	
	def defineProperties() {	
		java.util.Properties properties = new java.util.Properties()
		properties.put("user", "giuliano")
		properties.put("password", "123456")
		
	}
	
	def connect(){
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		java.util.Properties properties = new java.util.Properties()
		properties.put("user", "giuliano")
		properties.put("password", "123456")
		Connection con = driver.connect(connectTo, properties)
		sql = new Sql(con)
	}
	
	def ArrayList<?> fetchPathQueries() {
		/**
		 * Método que devuelve los path queries
		 */
		connect()
		def result = sql.rows "select * from V_Normalized"
		return result
	}
	def ArrayList<?> fetchConnectedPairs() {
		/**
		 * Método que devuelve los pares conectados
		 */
		connect()
		def result = sql.rows "select id, CONVERT(page using utf8) as Page from U_page"
		return result
	}
	
	def ArrayList<?> fetchNotConnectedPairs() {
		/**
		 * Método que devuelve los pares no conectados
		 */
		connect()
		def result = sql.rows "select * from NFPC"
		return result
	}
	
	def ArrayList<?> fetchPQRelevance() {
		/**
		 * Método que devuelve la cantidad de pares que conecta cada path query
		 */
		connect()
		//Limitado a 50 porque si no se va al carajo
		def result = sql.rows "select count(v_to) as cant,vn.path from UxV inner join V_Normalized as vn on UxV.v_to=vn.id group by v_to order by cant desc limit 50"
		return result
		
	}
	/**
	def ArrayList<?> pqJoinCp() {
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		defineProperties()
		Connection con = driver.connect(connectTo, properties)
		Sql sql = new Sql(con)
		def result = sql.rows "select vn.id as id_PQ,vn.path,up.id as id_CP,up.page from V_Normalized vn inner join UxV on vn.id=UxV.v_to inner join U_page up on UxV.u_from=up.id limit 10;"
		return result
		
	}
	**/
	def ArrayList<?> fetchConnectedPairsOfPQ(int id) {
		/**
		 * Método que devuelve los pares que conecta un path query
		 */
		connect()
		def result = sql.rows "select up.id, CONVERT(up.page using utf8) as Page from U_page up inner join UxV on up.id=UxV.u_from inner join V_Normalized vn on vn.id=UxV.v_to where vn.id="+id;
		return result
		
	}
	
	def ArrayList<?> fetchPQWhoConnects(int id) {
		/**
		 * Método que devuelve los path queries que conectan a un par
		 */
		connect()
		def result = sql.rows "select vn.id, vn.path from U_page up inner join UxV on up.id=UxV.u_from inner join V_Normalized vn on vn.id=UxV.v_to where up.id="+id;
		return result
		
	}
}
