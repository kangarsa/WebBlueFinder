package webbluefinder

import grails.converters.JSON
import groovy.json.*

/**
 * v0.1
 * Controlador encargado de administrar lo relacionado a los Path Queries. Obtiene los pares conectados
 * a partir del modelo, y los pasa a la vista
 * 
 * @author giuliano
 *
 */
class PathQueryController {
	
	def updateTree() {
		def from = params.from
		render pathsMap(from) as JSON
	}
	
    def index() { 
		/**
		 * Este index podría listar todos los PQ, hay que ver si tiene sentido, ya que, en principio,
		   de eso se encarga ShowStatisticsController (junto al resto de las estadísticas) 
		 */
	}
	def show(int id) { 
		/**
		 * Defino una instancia de la clase de dominio ShowStatistics, para pedirle 
		 * los resultados de las consultas ahí definidas
		 */
		def st = new ShowStatistics()
		PathQuery pq = st.fetchPathQuery(id) //Obtengo el DTO del PathQuery
		//def PQConnectedPairs = st.fetchConnectedPairsOfPQ(id) //Obtengo los connected pairs del path query indicado por parámetro
		//def PQRelevance = st.fetchPQRelevance(id) //Obtengo la relevancia del path query

		/**
		 * En la siguiente colección agrego los pares conectados  'instanciados' del 
		 * path query. 
		 */
		def instantiatedCPs = new ArrayList<String>()
		
		//Defino un mapa (diccionario) para armar el árbol de caminos
		def pathsMap = new TreeMap()
		
		for (connectedPair in pq.getConnectedPairs()) {

			def pageElements = connectedPair.Page.split() //Aplico un split al string que contiene a las dos páginas conectadas, para obtenerlas por separado
			def from = pageElements.getAt(0)
			//En getAt(1) está la coma separadora
			def to = pageElements.getAt(2)
			/**
			 * Reemplazo los '#from' y '#to' del path por los valores específicos de cada par de
			 * páginas de conecta, y reemplazo el 'Cat' por 'Categoría' para que sea navegable a
			 * la URL de Wikipedia. NOTA: para diferentes versiones de idioma, 'Cat' debe
			 * reemplazarse como corresponda (en inglés; 'Cat' por 'Category')
			 */
			instantiatedCPs.add( pq.getPath().replaceAll('#from', from).replaceAll('#to', to).replace("Cat", "Categoría"))
			if (pathsMap.containsKey(from))
				pathsMap[from].add(to)
				else
					pathsMap.put(from, [to])
		}
		//println coll
		//println pp
		/**
		 * Obtengo los [5 primeros] path queries más relevantes
		 */
		def first5PQs = st.fetchPQRelevance("limit 5");
		def jasonisedPathsMap = (pathsMap as JSON).toString()
		def formattedPathsMap = jasonisedPathsMap.replace("{", "{\"name\": ").replaceAll("],", "]},{\"name\": ").replaceAll("\",","\"},{\"name\": ").replaceAll(":\\[", ", \"children\": [{\"name\": ").replaceAll("]}", "}]").replaceAll("]", "]}")
		render(view:"show.gsp", model:['pqcp':pq.getConnectedPairs(),
									   'path':pq.getPath(), 
									   'pqr': first5PQs, 
									   'PQrel': pq.getRelevance(), 
									   'instanciated': instantiatedCPs,
									   'pathsMap':formattedPathsMap])
	}
	
}
