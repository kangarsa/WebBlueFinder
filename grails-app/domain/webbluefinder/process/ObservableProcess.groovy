package webbluefinder.process

import webbluefinder.Scene;


class ObservableProcess {
	static belongsTo = [observer: Scene]
	
	//static belongsTo = [Scene]
	//static hasOne = [observer : Scene]
	
    static constraints = {
		observer editable:false, display:true, unique:false
		//oldObserver nullable:true, editable:false, display:true, unique:false
    }
	
	ObservableProcess (Scene o) {
		this()
		observer = o
	}
	/*
	def setObserver(Scene o) {
		if(o != null) {
			oldObserver = observer
		}
		observer = o
	}
	def addObserver(Scene o) {
		setObserver(o)
	}
	*/
	def removeObserver(Scene o) {
		observer = null
	}
	
	def removeObserver() {
		observer = null
	}
	
	def notifyErrorsToObservers() {
		observer.updateProcessErrors()
	}
	
	def notifyStateToObservers() {
		observer.updateProcessState()
	}
	
	def notifyFinalizedToObservers() {
		observer.updateFinalized()
	}
	
	def notifyComputingToObservers() {
		observer.updateComputing()
	}
	
	def notifyStoppedToObservers() {
		observer.updateStopped()
	}
	
	def notifyStateChangeToObservers() {
		observer.updateProcessState()
	}
}
