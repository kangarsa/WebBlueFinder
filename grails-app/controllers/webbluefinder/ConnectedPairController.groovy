package webbluefinder

class ConnectedPairController {

    def index() { }
	def show(int id, String connected) {
		def st = new ShowStatistics()
		def PQWhichConnect = st.fetchPQWhoConnects(id)
		
		def coll = new ArrayList<String>()
		
		for (item in PQWhichConnect) {
			//println item.Page.split().getAt(0)
			def split = connected.split()
			def from = split.getAt(0)
			def to = split.getAt(2)
			coll.add( item.path.replaceAll('#from', from).replaceAll('#to', to).replace("Cat", "Categoría"))
		}
		
		
		render(view:"show.gsp", model:['pqc':PQWhichConnect, 'connected':connected, 'instanciated':coll])
	}
}
