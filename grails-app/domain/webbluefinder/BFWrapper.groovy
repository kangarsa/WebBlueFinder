package webbluefinder


class BFWrapper extends Process {

    static constraints = {
    }
	
	def getName() {
		return 'BFWrapper'
	}
	
	def execute() {
		processState = 'Computing'
		//do execute
	}
}
