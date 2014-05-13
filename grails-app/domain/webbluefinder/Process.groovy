package webbluefinder

class Process {
	String processErrors
	ProcessState state
	Scene scene
	
	static mapping = {
		processErrors type: 'text'
	}

    static constraints = {
		state nullable:true, editable:false, display:false
		scene nullable:true
		processErrors nullable:true, editable:false, display:true
    }
	
	Process() {
		this.processErrors = ''
		this.state = new NotStarted()
	}
	
	Process(Scene s) {
		this()
		this.scene = s
	}

	def setAndNotifyProcessErrors(String e) {
		//processState.setStateErrors(e)
		setProcessErrors(e)
		notifyErrorsToObservers()
	}
	
	def setProcessState(ProcessState s) {
		state = s
		notifyStateToObservers()
	}
			
	def getName() {
		return 'AbstractProcess'
	}
	
	def isNotStarted() {
		return state.isNotStarted()
	}
	def isStoped() {
		return state.isStoped()
	}
	def isFinalized() {
		return state.isFinalized()
	}
	def isComputing() {
		return state.isComputing()
	}
	
	def canExecute() {
		return isNotStarted()
	}
	
	def setStoped() {
		this.setState(new Stoped())
		notifyStopedToObservers()
	}
	def setComputing() {
		this.setState(new Computing())
		notifyComputingToObservers()
	}
	def setFinalized() {
		//this.setState(new Finalized())
		//this.merge()
		System.out.println("acá esta")
		state = new Finalized()
		this.save()
		System.out.println(state)
		//notifyFinalizedToObservers()
	}

	def hasProcessErrors() {
		return processErrors != ''
	}
	
	def totalSteps () {
		return 3
	}
	
	def getStateStep() {
		// -1 = stoped, 0 = notStarted, 1 = isComputing, 2 = isFinalized
		return state.getStep()
	}
	
	def getStateName() {
		return state.getName()
	}
		
	def nextState() {
		state = state.getNextState()
		notifyStateChangeToObservers()
	}
	
	
	def execute() {
		if(this.isNotStarted()) {
			this.nextState()
			this.start()
			return true
		}
		return false
	}
	
	def nextProcess() {
		if(isFinalized()) {
			scene.setNewProcess(this.getNextProcess())
			return true;
		}
		return false;
	}
	def forceNextProcess() {
		scene.setNewProcess(this.getNextProcess())
	}
	
	def getPercent(){
		if(this.isFinalized()) {
			return 100
		} else if(this.isComputing()) {
			return 50
		}
		return 0
	}
	
	/** Observer Methods start **/
	
	def removeObserver(Scene o) {
		scene = null
	}
	
	def removeObserver() {
		scene = null
	}
	
	def notifyErrorsToObservers() {
		scene.updateProcessErrors()
	}
	
	def notifyStateToObservers() {
		scene.updateProcessState()
	}
	
	def notifyFinalizedToObservers() {
		scene.updateFinalized()
	}
	
	def notifyComputingToObservers() {
		scene.updateComputing()
	}
	
	def notifyStopedToObservers() {
		scene.updateStoped()
	}
	
	def notifyStateChangeToObservers() {
		scene.updateProcessState()
	}
	
	
	/** Observer Methods ends **/
	
	// subclass responsibility:
	def start() {}
	def getResults() {}
	def getNextProcess() {}
	def isDBRetrieverWrapper() {}
	def isPIAWrapper() {}
	def isBFWrapper() {}
	def hasNextProcess() {}
	def getProcessStep() {
		// -1 = stoped, 0 = notStarted, 1 = isComputing, 2 = isFinalized
	}
	
}
