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
		render(view:"show.gsp", model:['pqcp':PQConnectedPairs, 'path':path])
	}
}
