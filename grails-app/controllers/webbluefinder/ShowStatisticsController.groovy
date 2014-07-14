package webbluefinder

/**
 * v0.1
 * Controlador encargado de "armar" las estadísticas relacionadas a un escenario. En primera instancia
 * muestra los pares conectados, los path queries, los pares no conectados, y la relevancia de cada
 * path query en función de los pares que conecta (es decir, la cantidad de pares que conecta cada path
 * query). Para esto directamente lee de las tablas U_page, V_Normalized, NFPC, y UxV (consultando a la
 * clase de dominio ShowStatistics, mediante los métodos correspondientes). 
 * Más adelante debería expandirse para mostrar otras estadísticas relevantes del escenario.
 * @author giuliano
 *
 */

class ShowStatisticsController {

    def index() {
		def st = new ShowStatistics()
		def pq = st.fetchPathQueries()
		def cp = st.fetchConnectedPairs()
		def ncp = st.fetchNotConnectedPairs()
		def pqr = st.fetchPQRelevance()
		//def foo = st.fetchPathQuery(4)
		//println foo.path
		def someoneElsesPage = new URL( "http://en.wikipedia.org/wiki/Popular_Action_(Peru)" ).getText()
		println someoneElsesPage
		
		render(view:"index.gsp", model:['pq':pq, 'cp':cp, 'ncp':ncp, 'pqr':pqr])
		
	}
	/** Lo que sigue a continuación ya no correspondería con este controlador,
	 * lo dejo por el momento por si llego a necesitarlo en otro lado :)
	 */
	/**
	def showConnectedByPQ(int id, String path) {
		def st = new ShowStatistics()
		def PQConnectedPairs = st.fetchConnectedPairsOfPQ(id)
		render(view:"showConnectedByPQ.gsp", model:['pqcp':PQConnectedPairs, 'path':path])
	}
	
	def showPQWhoConnects(int id, String page) {
		def st = new ShowStatistics()
		def PQConnectedPairs = st.fetchPQWhoConnects(id)
		render(view:"showPQWhoConnects.gsp", model:['pqcp':PQConnectedPairs, 'page':page])	
	}
	**/
}
