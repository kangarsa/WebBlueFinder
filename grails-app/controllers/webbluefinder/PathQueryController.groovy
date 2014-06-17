package webbluefinder

//import webbluefinder.ShowStatistics

class PathQueryController {

    def index() { }
	def show(int id, String path) { 
		def st = new ShowStatistics()
		def PQConnectedPairs = st.fetchConnectedPairsOfPQ(id)
		render(view:"show.gsp", model:['pqcp':PQConnectedPairs, 'path':path])
	}
}
