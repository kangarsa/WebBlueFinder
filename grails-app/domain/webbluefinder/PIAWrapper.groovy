package webbluefinder


class PIAWrapper extends Process {

    static constraints = {
    }
	
	def getName() {
		return 'PIAWrapper'
	}
	
	def execute() {
		processState = 'Computing'
		//do execute
	}
}
