package webbluefinder

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException

class Properties {
	String title = "Web BlueFinder"
	String hostname = "localhost"
	String database = "results"
	String dbuser = "results"
	String dbpass = "results"
	String dbImportedPIA = "piaImportAux"
	String dbUserImportedPIA = "root"
	String dbPassImportedPIA = "root"

    static constraints = {
		title blank:false, nullable:false, display:true, default:"Web BlueFinder"
	    hostname blank:false, nullable:false, display:true, default:"localhost"
	    database blank:false, nullable:false, display:true, default:"results"
	    dbuser blank:false, nullable:false, display:true, default:"results"
		dbpass blank:false, nullable:false, display:true, default:"results"
    }
	
	static mapping = {
		table 'prop'
		hostname column: 'hname'
		database column: 'db'
	}
	
	
	static getLast(){
		Properties p = Properties.last()		
		if (p == null) {
			return new Properties()
		}
		return p
	}
}
