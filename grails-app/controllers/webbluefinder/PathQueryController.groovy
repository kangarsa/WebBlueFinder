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
		
		/**
		 * Obtengo el DTO del PathQuery
		 */
		PathQuery pq = st.fetchPathQuery(id) 
		/**
		 * En la siguiente colección agrego los pares conectados  'instanciados' del 
		 * path query. 
		 */
		def instantiatedCPs = new ArrayList<String>()

		for (connectedPair in pq.getConnectedPairs()) {
			/**
			 * connectedPair tiene la forma '#pageFrom , #pageTo'
			 * Aplico un split al string que contiene a las dos páginas conectadas, para obtenerlas por separado
			 */
			def pageElements = connectedPair.Page.split() 
			def from = pageElements.getAt(0)
			/**
			 * En getAt(1) está la coma separadora
			 */
			def to = pageElements.getAt(2)
			/**
			 * Reemplazo los '#from' y '#to' del path por los valores específicos de cada par de
			 * páginas de conecta, y reemplazo el 'Cat' por 'Categoría' para que sea navegable a
			 * la URL de Wikipedia. NOTA: para diferentes versiones de idioma, 'Cat' debe
			 * reemplazarse como corresponda (en inglés; 'Cat' por 'Category')
			 */
			instantiatedCPs.add( pq.getPath().replaceAll('#from', from).replaceAll('#to', to).replace("Cat", "Categoría"))
		}
		
		/**
		 * Defino un mapa (diccionario) para armar el árbol de caminos
		 */
		def pathsMap = new TreeMap()
		
		def ite = instantiatedCPs.iterator()
		/**
		 * Itero sobre los connectedPair instanciados para generar un mapa a partir del cual armo el árbol de caminos
		 */
		while (ite.hasNext()) {
			String CP = ite.next()
			def splittedCP = CP.split()
			if (CP.contains('*')) { //Si tiene un '*' quiere decir que es un path con al menos un 'salto' entre el #from y el #to
				if (pathsMap.containsKey(splittedCP[0])) 
				  	if (pathsMap[splittedCP[0]].containsKey(splittedCP[4])) {
						def auxMap = new TreeMap()
						def auxSet = new HashSet()
						auxMap = pathsMap[splittedCP[0]]
						auxSet = auxMap[splittedCP[4]]
						auxSet.add(splittedCP[6])
						auxMap.put(splittedCP[4], auxSet)
						pathsMap.put(splittedCP[0], auxMap)
				  		}
						else {
							def auxMap = new TreeMap()
							def setTo = [splittedCP[6]] as Set
							auxMap = pathsMap[splittedCP[0]]
							auxMap.put(splittedCP[4], setTo)
							pathsMap.put(splittedCP[0], auxMap)
							}
					else {
						def auxMap = new TreeMap()
						def setTo = [splittedCP[6]] as Set
						auxMap.put(splittedCP[4], setTo)
						pathsMap.put(splittedCP[0], auxMap)
						}
			}
			else {
				if (pathsMap.containsKey(splittedCP[0])) {
					pathsMap[splittedCP[0]].add(splittedCP[2])
					}
					else
						pathsMap.put(splittedCP[0], [splittedCP[2]])
			}
		}

		/**
		 * Obtengo los [5 primeros] path queries más relevantes
		 */
		def first5PQs = st.fetchPQRelevance("limit 5");
		def jasonisedPathsMap = (pathsMap as JSON).toString()
		
		/**
		 * Reemplazo en el string del json para pasarlo al formato adecuado para armar el árbol
		 */
		def formattedPathsMap = jasonisedPathsMap.replace("{", "{\"name\": ").
								replaceAll("],", "]},{\"name\": ").
								replaceAll("\",","\"},{\"name\": ").
								replaceAll(":\\[", ", \"children\": [{\"name\": ").
								replaceAll("]}", "}]").
								replaceAll("]", "]}").
								replaceAll(":\\{", ", \"children\": [{").
								replaceAll("},\"", "}]},{\"name\": \"").
								replaceAll("}}", "}]}")
								
		render(view:"show.gsp", model:['pqcp':pq.getConnectedPairs(), //ArrayList
									   'path':pq.getPath(), //String
									   'pqr': first5PQs, //ArrayList
									   'PQrel': pq.getRelevance(), //ArrayList
									   'instantiated': instantiatedCPs, //ArrayList
									   'pathsMap':formattedPathsMap, //String
									   'treeSize':instantiatedCPs.size() * 13]) //Integer
		
	}
	
}
