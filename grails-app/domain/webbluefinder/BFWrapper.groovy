package webbluefinder


class BFWrapper extends Process {

    static constraints = {
    }
	
	BFWrapper(Scene s){
		super(s)
	}
	
	def getName() {
		return 'BFWrapper'
	}
	
	def start() {
		//do execute
	}
	
	def getNextProcess() {
		return this
	}
	
	def getProcessStep() {
		return 3
	}
	def isDBRetrieverWrapper() {
		return false
	}
	def isPIAWrapper() {
		return false
	}
	def isBFWrapper() {
		return true
	}
	def hasNextProcess() {
		return false
	}
}
