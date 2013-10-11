package webbluefinder

class AbstractProcess {
	private state
	private errors

    static constraints = {
    }
	
	def getName() {
		return 'AbstractProcess'
	}
	
	def isFinalized() {
		return state == 'finalized'
	}
	def isComputing() {
		return state == 'computing'
	}
	def hasErrors() {
		return errors != null
	}
	def getResults() {
		// subclass responsibility
	}
	
	def totalSteps = 3
	
	def getStep() {
		// 0 = notStarted, 1 = isComputing, 2 = isFinalized
		if (isFinalized()) {
			return 2
		} else if (isComputing()) {
			return 1
		} else {
			return 0
		}
	}
	
	def execute() {
		// subclass responsibility
	}
	
}
