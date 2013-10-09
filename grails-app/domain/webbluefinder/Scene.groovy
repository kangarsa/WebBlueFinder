package webbluefinder

class Scene {
    String fromType
    String toType
    String property
	boolean queryable

    static constraints = {
		queryable default: false
		
    }
	
}
