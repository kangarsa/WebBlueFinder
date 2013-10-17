package webbluefinder

//class AbstractProcess extends AbstractObservableProcess {
class AbstractProcess {
	String state
	String processErrors

    static constraints = {
		processErrors editable:false
		state inList: ["", "computing", "finalized"]
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
	def setFinalized() {
		state = 'finalized'
		notifyFinalizedToObservers()
	}
	def setComputing() {
		state = 'computing'
	}
	def setProcessErrors(e) {
		processErrors = e
		notifyErrorsToObservers()
	}
	def hasProcessErrors() {
		return processErrors != null
	}
	def getProcessErrorss() {
		return processErrors
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
