package webbluefinder

import wbflisteners.ObservableProcess;
import wbflisteners.ProcessesListener;
import bflaunchers.BFEvaluationLauncher


class BFEvaluationWrapper extends Process implements ProcessesListener {

    static constraints = {
    }
	
	BFEvaluationWrapper(Scene s){
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
			System.out.println("?BFE-1")
			def bfe = new BFEvaluationLauncher()
			System.out.println("?BFE-2")
			bfe.addObserver(this)
			
			System.out.println("?BFE-3")
			bfe.launch(p.getDatabase(),p.getDbuser(), p.getDbpass(),"sc"+scene.id+"_bfe"+id, 100)
		}
		System.out.println("workerBFE.poststart()")
	}
	
	def getNextProcess() {
		return this
	}
	
	def getProcessStep() {
		return 3
	}
	def isBFEvaluationWrapper() {
		return true
	}
	def hasNextProcess() {
		return false
	}

	@Override
	public void computing(ObservableProcess p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopped(ObservableProcess p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notStarted(ObservableProcess p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalized(ObservableProcess p) {
		System.out.println("en finalized");
		this.setFinalized()
	}
}
