package webbluefinder

import grails.test.mixin.TestFor
import groovy.sql.Sql
import java.sql.Connection
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ShowStatistics)
class ShowStatisticsSpec extends Specification {
	
	def sql
	
	def setup() {
		connect()
	}

	def cleanup() {
		sql.execute "delete from V_Normalized"
		sql.execute "delete from U_page"
		sql.execute "delete from NFPC"
		//sql.execute "delete from UxV"
		
	}
	
	def connect(){
		def connectTo = "jdbc:mysql://localhost/piaTest"
		def driver = Class.forName("com.mysql.jdbc.Driver").newInstance()
		java.util.Properties properties = new java.util.Properties()
		properties.put("user", "root")
		properties.put("password", "root")
		Connection con = driver.connect(connectTo, properties)
		sql = new Sql(con)
	}

    void "test fetchPathQueries"() {
		given:
			//Cargar un arreglo con valores a comparar con el resultante de la consulta
			def pathQueriesTestArray = new ArrayList<?>()
			pathQueriesTestArray.add(["id":4, "path": '#from / #to'])
			pathQueriesTestArray.add(["id":5, "path": '#from / * / Cat:#from_(Venezuela)_politicians / #to'])
			pathQueriesTestArray.add(["id":6, "path": '#from / * / Cat:#to / #to'])
			pathQueriesTestArray.add(["id":149, "path": '#from / * / Cat:Alcaldes_del_Partido_Socialista_Francés / #to'])
			pathQueriesTestArray.add(["id":150, "path": '#from / * / Cat:#from_MEPs / #to'])
			pathQueriesTestArray.add(["id":151, "path": '#from / * / Cat:Spanish_Socialist_Workers\'_Party_politicians / #to'])
	
			//Insertar tuplas en la base auxiliar piaTest
		when:	
			sql.execute("INSERT INTO `V_Normalized` VALUES"+ 
							"(4,'#from / #to'),"+
							"(5,'#from / * / Cat:#from_(Venezuela)_politicians / #to'),"+
							"(6,'#from / * / Cat:#to / #to'),"+
							"(149,'#from / * / Cat:Alcaldes_del_Partido_Socialista_Francés / #to'),"+
							"(150,'#from / * / Cat:#from_MEPs / #to'),"+
							"(151,'#from / * / Cat:Spanish_Socialist_Workers''_Party_politicians / #to')")
						
			def st = new ShowStatistics("localhost","piaTest", "root", "root")
			ArrayList<?> pathQueries = st.fetchPathQueries()
		then:
			pathQueriesTestArray == pathQueries
		
    }
	
	void "test fetchConnectedPairs"() {
		given:
		//Cargar un arreglo con valores a comparar con el resultante de la consulta
			def connectedPairsTestArray = new ArrayList<?>()
			connectedPairsTestArray.add(["id":19, "Page": 'Acción_Social_(Italia) , Alessandra_Mussolini'])
			connectedPairsTestArray.add(["id":21, "Page": 'Alianza_90/Los_Verdes , Cem_Özdemir'])
			connectedPairsTestArray.add(["id":41, "Page": 'Partido_Liberal-Conservador , Antonio_Cánovas_del_Castillo'])
			connectedPairsTestArray.add(["id":2362, "Page": 'Partido_Demócrata_de_los_Estados_Unidos , Mary_Landrieu'])
			connectedPairsTestArray.add(["id":2363, "Page": 'Partido_Demócrata_de_los_Estados_Unidos , Max_Baucus'])
			connectedPairsTestArray.add(["id":2364, "Page": 'Partido_Demócrata_de_los_Estados_Unidos , Michael_Dukakis'])

		//Insertar tuplas en la base auxiliar piaTest
	when:
		sql.execute('''
						INSERT INTO `U_page` VALUES 
						(19,'Acción_Social_(Italia) , Alessandra_Mussolini'),
						(21,'Alianza_90/Los_Verdes , Cem_Özdemir'),
						(41,'Partido_Liberal-Conservador , Antonio_Cánovas_del_Castillo'),
						(2362,'Partido_Demócrata_de_los_Estados_Unidos , Mary_Landrieu'),
						(2363,'Partido_Demócrata_de_los_Estados_Unidos , Max_Baucus'),
						(2364,'Partido_Demócrata_de_los_Estados_Unidos , Michael_Dukakis')
					''')
					
		def st = new ShowStatistics("localhost","piaTest", "root", "root")
		ArrayList<?> connectedPairs = st.fetchConnectedPairs()
	then:
		connectedPairsTestArray == connectedPairs
	}
	
	
	void "test fetchNotConnectedPairs"() {
		given:
		//Cargar un arreglo con valores a comparar con el resultante de la consulta
			def notConnectedPairsTestArray = new ArrayList<?>()
			notConnectedPairsTestArray.add(["id":19, "v_from": "Partido_Liberal_(España,_Restauración)", "u_to": "Niceto_Alcalá-Zamora"])
			notConnectedPairsTestArray.add(["id":20, "v_from": 'Partido_Liberal_(Grecia)', "u_to": "Alexandros_Diomidis"])
			notConnectedPairsTestArray.add(["id":21, "v_from": 'Partido_Liberal_(Venezuela)', "u_to": "Francisco_Linares_Alcántara"])
			notConnectedPairsTestArray.add(["id":114, "v_from": 'Partido_Liberal_de_Canadá', "u_to": "Alexander_Mackenzie_(político)"])

		//Insertar tuplas en la base auxiliar piaTest
	when:
		sql.execute('''
						INSERT INTO `NFPC` VALUES 
						(19,'Partido_Liberal_(España,_Restauración)','Niceto_Alcalá-Zamora'),
						(20,'Partido_Liberal_(Grecia)','Alexandros_Diomidis'),
						(21,'Partido_Liberal_(Venezuela)','Francisco_Linares_Alcántara'),
						(114,'Partido_Liberal_de_Canadá','Alexander_Mackenzie_(político)')
					''')
					
		def st = new ShowStatistics("localhost","piaTest", "root", "root")
		ArrayList<?> notConnectedPairs = st.fetchNotConnectedPairs()
	then:
		notConnectedPairsTestArray == notConnectedPairs
		
	}
	void "test fetchPQRelevance all"() {
		given:
		//Cargar un arreglo con valores a comparar con el resultante de la consulta
			def PQRelevanceTestArray = new ArrayList<?>()
			PQRelevanceTestArray.add(["id":19, "v_from": "Partido_Liberal_(España,_Restauración)", "u_to": "Niceto_Alcalá-Zamora"])
			PQRelevanceTestArray.add(["id":20, "v_from": 'Partido_Liberal_(Grecia)', "u_to": "Alexandros_Diomidis"])
			PQRelevanceTestArray.add(["id":21, "v_from": 'Partido_Liberal_(Venezuela)', "u_to": "Francisco_Linares_Alcántara"])
			PQRelevanceTestArray.add(["id":114, "v_from": 'Partido_Liberal_de_Canadá', "u_to": "Alexander_Mackenzie_(político)"])

		//Insertar tuplas en la base auxiliar piaTest
	when:
		sql.execute('''
						INSERT INTO `NFPC` VALUES 
						(19,'Partido_Liberal_(España,_Restauración)','Niceto_Alcalá-Zamora'),
						(20,'Partido_Liberal_(Grecia)','Alexandros_Diomidis'),
						(21,'Partido_Liberal_(Venezuela)','Francisco_Linares_Alcántara'),
						(114,'Partido_Liberal_de_Canadá','Alexander_Mackenzie_(político)')
					''')
					
		def st = new ShowStatistics("localhost","piaTest", "root", "root")
		ArrayList<?> PQRelevance = st.fetchNotConnectedPairs()
	then:
		PQRelevanceTestArray == PQRelevance
		
	}
	void "test fetchPQRelevance id"() {
	}
	void "test fetchConnectedPairsOfPQ"() {
	}
	void "test fetchPQWhoConnects"() {
	}
	void "test fetchPathQuery"() {
	}
	void "test fetchConnectedPair"() {
	}
}
