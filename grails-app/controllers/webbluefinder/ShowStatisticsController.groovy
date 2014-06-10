package webbluefinder



class ShowStatisticsController {

    def index() {
		def st = new ShowStatistics()
		def pq = st.fetchPathQueries()
		def cp = st.fetchConnectedPairs()
		def ncp = st.fetchNotConnectedPairs()
		def pqr = st.fetchPQRelevance()
		def pqjoincp = st.pqJoinCp()
		
		render(view:"index.gsp", model:['pq':pq, 'cp':cp, 'ncp':ncp, 'pqr':pqr, 'pqcp':pqjoincp,'test':'holaTest'])
		
	}
}
