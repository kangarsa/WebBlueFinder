package webbluefinder

import bflaunchers.BFEvaluationLauncher


class BFEvaluationWrapper2 extends Process {

    static constraints = {
    }
	
	BFEvaluationWrapper2(Scene s){
		super(s)
	}
	
	def getName() {
		return 'BFEvaluationWrapper'
	}
	
	def start() {
		//do execute
		Properties p = Properties.getLast()
		System.out.println("workerBFE.prestart()")
		runAsync {
			System.out.println("nuevoThread?BFE")
			def bfe = new BFEvaluationLauncher()
			bfe.addObserver(this)
			bfe.launch("sc1Evaluation", 3)
		}
		System.out.println("workerBFE.poststart()")
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
