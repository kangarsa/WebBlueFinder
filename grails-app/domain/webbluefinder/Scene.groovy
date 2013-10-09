package webbluefinder

class Scene {
    String fromType
    String toType
    String property
	boolean queryable
	AbstractProcess process;

    static constraints = {
	    fromType blank:false
	    toType blank:false
	    property blank:false
		queryable default:false
		process nullable: true
    }
	
}
