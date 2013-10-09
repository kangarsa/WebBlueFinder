package webbluefinder

class FrontendController {

    //def index() { }
	
	def sceneService
	
    def list() {
        def filterlist = sceneService.listQueryables()
		render(view:'listQueryableScenes', sortInstanceList:filterlist)
    }
	
}
