package webbluefinder

class Finalized extends ProcessState {

    static constraints = {
    }
	
	def getName() {
		return 'Finalized'
	}
	def getStep() {
		return 2
	}
	def isStoped() {
		return false
	}
	def isNotStarted() {
		return false
	}
	def isComputing() {
		return false
	}
	def isFinalized() {
		return true
	}
	def getNextState() {
		return this
	}
}
