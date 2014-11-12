package webbluefinder.state

class Computing extends ProcessState {

    static constraints = {
    }
	
	def getName() {
		return 'Computing'
	}
	def getStep() {
		return 1
	}
	def isStoped() {
		return false
	}
	def isNotStarted() {
		return false
	}
	def isComputing() {
		return true
	}
	def isFinalized() {
		return false
	}
	def getNextState() {
		return new Finalized()
	}
}
