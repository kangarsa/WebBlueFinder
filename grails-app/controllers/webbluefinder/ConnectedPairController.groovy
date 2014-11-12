package webbluefinder

class ConnectedPairController {

    def index() { }
	
	def getInfobox(String page) {
		if (page.contains("<table class=\"infobox")) {
			def pos = page.indexOf("<table class=\"infobox")
			//println "pos antes del while: "+ pos
			def posClosing = page.indexOf("</table>", pos)
			//println "posClosing antes del while: "+posClosing
			def endPage = page.indexOf(page.size()-1)
			//String subPage = page[pos+1..posClosing+1]
			while (page[pos+2..posClosing+1].indexOf("<table") != -1) {
				//println "pre pos: " + page[pos+2..posClosing+1].indexOf("<table")
				//println "substring end: " + page.indexOf(pos+100)	
				pos = pos + page[pos+1..posClosing+1].indexOf("<table")
				if (page[posClosing+1..endPage].indexOf("</table>")!=-1) {
					//println "posClosing antes: "+ posClosing
					//println "pre posClosing: "+ page[posClosing+1..endPage].indexOf("</table>")
					posClosing = posClosing + page[posClosing+1..endPage].indexOf("</table>")
					}
					else posClosing = endPage-1
				//println "pos: "+ pos
				//println "page en la pos "+page[pos]
				//println "posClosing: "+ posClosing
			}			
			//println "index del infobox: "+ page.indexOf("<table class=\"infobox")
			
			def infobox = page[page.indexOf("<table class=\"infobox")..posClosing+7]
			/** Lo siguiente es para convertir los paths relativos en absolutos en los infobox**/
			def infoboxAbsolutePaths = infobox.replaceAll("/wiki/", "http://es.wikipedia.org/wiki/")
			return infoboxAbsolutePaths
			}
			else return "Infobox not available"
	}
	
	def show(int id) {
		def st = new ShowStatistics()
		ConnectedPair cp = st.fetchConnectedPair(id)
		def split = cp.getPage().split()
		def from = split.getAt(0)
		def to = split.getAt(2)
		def concretePaths = new ArrayList<String>()
		for (item in cp.getPathQueries()) 	
			concretePaths.add( item.path.replaceAll('#from', from).replaceAll('#to', to).replace("Cat", "Categoría"))
			
			
		def infoboxes = new ArrayList<?>()
		infoboxes.add(getInfobox(new URL( "http://es.wikipedia.org/wiki/"+from ).getText()))
		infoboxes.add(getInfobox(new URL( "http://es.wikipedia.org/wiki/"+to ).getText()))

		render(view:"show.gsp", model:['pqc':cp.getPathQueries(), //ArrayList
									   'connected':cp.getPage().replace(",","-").replaceAll("_", " "), //String
									   'concretePaths':concretePaths, //ArrayList
									   'infoboxes':infoboxes]) //ArrayList
	}
}
