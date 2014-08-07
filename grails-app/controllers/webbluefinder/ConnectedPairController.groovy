package webbluefinder

class ConnectedPairController {

    def index() { }
	def show(int id, String connected) {
		def st = new ShowStatistics()
		def PQWhichConnect = st.fetchPQWhoConnects(id)
		
		def coll = new ArrayList<String>()
		def colPages = new ArrayList<?>()
		
		for (item in PQWhichConnect) {
			//println item.Page.split().getAt(0)
			def split = connected.split()
			def from = split.getAt(0)
			def to = split.getAt(2)
			coll.add( item.path.replaceAll('#from', from).replaceAll('#to', to).replace("Cat", "Categoría"))
			def fromPage = new URL( "http://es.wikipedia.org/wiki/"+from ).getText()
			//println "fromPage:"+fromPage
			if (fromPage.contains("<table class=\"infobox")) {
				def fromInfobox = fromPage[fromPage.indexOf("<table class=\"infobox")..fromPage.indexOf("</table>", fromPage.indexOf("<table class=\"infobox"))+7]		
			/** Lo siguiente es para convertir los paths relativos en absolutos en los infobox**/
				def fromInfoboxAbsolutePaths = fromInfobox.replaceAll("/wiki/", "http://es.wikipedia.org/wiki/")
				colPages.add(fromInfoboxAbsolutePaths)
			}
			else {
				colPages.add("Infobox not available")
			}
			//println fromPage.indexOf("<table class=\"infobox")
			//println fromInfobox
			def toPage = new URL( "http://es.wikipedia.org/wiki/"+to ).getText()
			if (toPage.contains("<table class=\"infobox")) {
				def toInfobox = toPage[toPage.indexOf("<table class=\"infobox")..toPage.indexOf("</table>", toPage.indexOf("<table class=\"infobox"))+7]
				def toInfoboxAbsolutePaths = toInfobox.replaceAll("/wiki/", "http://es.wikipedia.org/wiki/")
				colPages.add(toInfoboxAbsolutePaths)
			}
			else {
				colPages.add("Infobox not available")
			}
		}
		def test = colPages.getAt(0)
		println colPages.size
		//println test
		
		render(view:"show.gsp", model:['pqc':PQWhichConnect, 'connected':connected, 'instanciated':coll, 'test':colPages])
	}
}
