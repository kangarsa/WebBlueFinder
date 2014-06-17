package webbluefinder



class ShowStatisticsController {

    def index() {
		def st = new ShowStatistics()
		def pq = st.fetchPathQueries()
		def cp = st.fetchConnectedPairs()
		def ncp = st.fetchNotConnectedPairs()
		def pqr = st.fetchPQRelevance()

		
		render(view:"index.gsp", model:['pq':pq, 'cp':cp, 'ncp':ncp, 'pqr':pqr])
		
	}
	
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
}
