package webbluefinder

class NotStarted extends ProcessState {

    static constraints = {
    }
	
	def getName() {
		return 'Not Started'
	}
	def getStep() {
		return 0
	}
	def isStoped() {
		return false
	}
	def isNotStarted() {
		return true
	}
	def isComputing() {
		return false
	}
	def isFinalized() {
		return false
	}
	def getNextState() {
		return new Computing()
	}
}
