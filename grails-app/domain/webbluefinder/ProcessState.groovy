package webbluefinder

class ProcessState {
//	Process process
	static belongsTo = [Process,Scene]

    static constraints = {
    }
	
	def getStep() {}
	def getName() {}
	def isStoped() {}
	def isNotStarted() {}
	def isComputing() {}
	def isFinalized() {}
	def getNextState() {}
	
}
