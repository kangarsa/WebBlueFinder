package webbluefinder

import bflaunchers.PIALauncher
import wbflisteners.ObservableProcess
import wbflisteners.ProcessesListener


class PIAWrapper extends Process implements ProcessesListener {

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
		Properties p = Properties.getLast()
		System.out.println("workerPIA.prestart()")
		runAsync {
			System.out.println("nuevoThread?");
			def pia = new PIALauncher()
			pia.addObserver(this)
			pia.launch(scene.piaMinLimit, scene.piaMaxLimit, scene.piaIterationsLimit, scene.id+"_dbr", scene.piaClean);
		}
		System.out.println("workerPIA.poststart()")
	}
	
	def getNextProcess() {
		return new BFRecommenderWrapper(scene)
	}
	
	def getProcessStep() {
		return 2
	}
	def isPIAWrapper() {
		return true
	}
	def hasNextProcess() {
		return true
	}

	@Override
	public void computing(ObservableProcess pia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopped(ObservableProcess pia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notStarted(ObservableProcess pia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalized(ObservableProcess pia) {
		System.out.println("en finalized");
		this.setFinalized()
	}
}
