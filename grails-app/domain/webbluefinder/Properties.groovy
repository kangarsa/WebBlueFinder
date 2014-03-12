package webbluefinder

class Properties {
	String title
	String hostname
	String database
	String dbuser
	String dbpass

    static constraints = {
		title blank:false, nullable:false, display:true, default:"Web BlueFinder"
	    hostname blank:false, nullable:false, display:true, default:"localhost"
	    database blank:false, nullable:false, display:true, default:"results"
	    dbuser blank:false, nullable:false, display:true, default:"results"
		dbpass blank:false, nullable:false, display:true, default:"results"
    }
	
	Properties (){
		this.title = "Web BlueFinder"
		this.hostname = "localhost"
		this.database = "results"
		this.dbuser = "results"
		this.dbpass = "results"
	}
	
	static getLast(){
		Properties p = Properties.last() 
		if(p == null){
			return new Properties()
		}
		return p
	}
}
