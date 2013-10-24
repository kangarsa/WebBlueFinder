package webbluefinder


class ObservableProcess {
	static belongsTo = [observer : Scene]

    static constraints = {
		observer display:false
    }

	ObservableProcess (Scene o) {
		observer = o
	}
	
	def addObserver(Scene o) {
		observer = o;
	}
	
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
}
