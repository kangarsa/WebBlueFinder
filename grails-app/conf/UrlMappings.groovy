class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/showStatistics/pathQuery/$id?" (resources: "pathQuery", includes: ['index', 'show'] )
		"/showStatistics/connectedPair/$id?" (resources: "connectedPair", includes: ['index', 'show'] )
		"/"(view:"/index")
		"500"(view:'/error')
	}
	
	
	/** static mappings = {
	 "/showStatistics/pathQuery/$id?" (resources: "pathQuery", includes: ['index', 'show'] )
	  
	}**/
}
