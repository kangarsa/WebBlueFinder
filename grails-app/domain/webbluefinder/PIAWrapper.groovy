package webbluefinder


class PIAWrapper extends Process {

    static constraints = {
    }
	
	PIAWrapper(Scene s){
		super(s)
	}
	
	def getName() {
		return 'PIAWrapper'
	}
	
	def start() {
		//do execute
	}
	
	def getNextProcess() {
		return new BFWrapper(scene)
	}
	
	def getProcessStep() {
		return 2
	}
	def isDBRetrieverWrapper() {
		return false
	}
	def isPIAWrapper() {
		return true
	}
	def isBFWrapper() {
		return false
	}
	def hasNextProcess() {
		return true
	}
}
