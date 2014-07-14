package webbluefinder

/**
 * v0.1
 * Controlador encargado de administrar lo relacionado a los Path Queries. Obtiene los pares conectados
 * a partir del modelo, y los pasa a la vista
 * 
 * @author giuliano
 *
 */
class PathQueryController {

    def index() { 
		/**Este index podría listar todos los PQ, hay que ver si tiene sentido, ya que, en principio,
		de eso se encarga ShowStatisticsController (junto al resto de las estadísticas) **/
	}
	def show(int id, String path) { 
		def st = new ShowStatistics()
		def PQConnectedPairs = st.fetchConnectedPairsOfPQ(id)
		def PQRelevance = st.fetchPQRelevance(id)
		//def pp = path.replaceAll('#from', 'Argentina').replaceAll('#to', 'Messi')
		//def paths = path.split()
		def coll = new ArrayList<String>()
		//ArrayList <?> lolo = (ArrayList) PQConnectedPairs
		//println lolo.class
		for (item in PQConnectedPairs) {
			//println item.Page.split().getAt(0)
			def split = item.Page.split()
			def from = split.getAt(0)
			def to = split.getAt(2)
			coll.add( path.replaceAll('#from', from).replaceAll('#to', to).replace("Cat", "Categoría"))
		}
		//println coll
		//println pp
		def first5PQs = st.fetchPQRelevance("limit 5");
		render(view:"show.gsp", model:['pqcp':PQConnectedPairs, 'path':path, 'pqr': first5PQs, 'PQrel': PQRelevance, 'instanciated': coll])
	}
}
