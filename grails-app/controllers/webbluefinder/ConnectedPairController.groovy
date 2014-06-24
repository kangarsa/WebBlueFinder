package webbluefinder

class ConnectedPairController {

    def index() { }
	def show(int id, String connected) {
		def st = new ShowStatistics()
		def PQWhichConnect = st.fetchPQWhoConnects(id)
		render(view:"show.gsp", model:['pqc':PQWhichConnect, 'connected':connected])
	}
}
