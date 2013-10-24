package webbluefinder


class Process extends ObservableProcess {
//class Process {
	String processState
	String processErrors
	
	static mapping = {
		processErrors type: 'text'
	}

    static constraints = {
		processState inList: ['Not Started', 'Computing', 'Finalized']
		processErrors editable:false, display:true
    }
	
	Process() {
		processState = 'Not Started'
		processErrors = ''
	}
/**
	def setProcessErrors(String e) {
		this.processErrors = e
		notifyErrorsToObservers()
	}
	String getProcessErrors() {
		return this.processErrors
	}
**/
	def setAndNotifyProcessErrors(String e) {
		this.processErrors = e
		notifyErrorsToObservers()
	}
	def setProcessState(String s) {
		this.processState = s
		notifyStateToObservers()
	}
			
	def getName() {
		return 'AbstractProcess'
	}
	def isFinalized() {
		return processState == 'Finalized'
	}
	def isComputing() {
		return processState == 'Computing'
	}
	def setFinalized() {
		processState = 'Finalized'
		notifyFinalizedToObservers()
	}
	def setComputing() {
		processState = 'Computing'
	}

	def hasProcessErrors() {
		return processErrors != null
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
