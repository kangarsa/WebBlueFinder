package webbluefinder

class DBRetrieverWrapper extends AbstractProcess {

    static constraints = {
    }
	
	def getName() {
		return 'DBRetrieverWrapper'
	}
	
	def execute() {
		state = 'computing'
		//do execute
	}
}
