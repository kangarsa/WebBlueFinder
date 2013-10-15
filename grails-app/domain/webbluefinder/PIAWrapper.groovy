package webbluefinder

class PIAWrapper extends AbstractProcess {

    static constraints = {
    }
	
	def getName() {
		return 'PIAWrapper'
	}
	
	def execute() {
		state = 'computing'
		//do execute
	}
}
