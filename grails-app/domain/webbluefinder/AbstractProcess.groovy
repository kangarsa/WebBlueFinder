package webbluefinder

class AbstractProcess {
	String state
	String errors

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
	def getErrors() {
		return errors
	}
	def getResults() {
		// subclass responsibility
	}
	
	def totalSteps () {
		return 3
	}
	
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
