package webbluefinder

class Stoped extends ProcessState {

    static constraints = {
    }
	
	def getName() {
		return 'Stoped'
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
