package webbluefinder.state

class Stopped extends ProcessState {

    static constraints = {
    }
	
	def getName() {
		return 'Stopped'
	}
	def getStep() {
		return -1
	}	
	def isStoped() {
		return true
	}
	def isNotStarted() {
		return false
	}
	def isComputing() {
		return false
	}
	def isFinalized() {
		return false
	}
	def getNextState() {
		return this
	}
}
