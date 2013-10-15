package webbluefinder

class BFWrapper extends AbstractProcess {

    static constraints = {
    }
	
	def getName() {
		return 'BFWrapper'
	}
	
	def execute() {
		state = 'computing'
		//do execute
	}
}
