<<<<<<< HEAD
class UrlMappings {

	static mappings = {

		/*
		 * Pages without controller
		 */
//		"/"				(view:"/index")
		"/about"		(view:"/siteinfo/about")
		"/blog"			(view:"/siteinfo/blog")
		"/systeminfo"	(view:"/siteinfo/systeminfo")
		"/contact"		(view:"/siteinfo/contact")
		"/terms"		(view:"/siteinfo/terms")
		"/imprint"		(view:"/siteinfo/imprint")
		"/nextSteps"	(view:"/home/nextSteps")

		/*
		 * Pages with controller
		 * WARN: No domain/controller should be named "api" or "mobile" or "web"!
		 */
        "/"	{
			controller	= 'home'
			action		= { 'index' }
            view		= { 'index' }
        }
		"/$controller/$action?/$id?"{
			constraints {
				controller(matches:/^((?!(api|mobile|web)).*)$/)
		  	}
		}

		/*
		 * System Pages without controller
		 */
		"403"	(view:'/_errors/403')
		"404"	(view:'/_errors/404')
		"500"	(view:'/_errors/error')
		"503"	(view:'/_errors/503')
	}
}
=======
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
>>>>>>> 66df2cacfb274f4503b8c913956b00b00d8d7795
