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
		System.out.println("workerPIA.prestart()A")
		def bl = scene.getBlacklist()
		System.out.println("workerPIA.prestart("+bl+")B")
		runAsync {
			System.out.println("PIA-nuevoThread?-1");
			def pia = new PIALauncher()
			System.out.println("PIA-nuevoThread?-2");
			pia.addObserver(this)
			System.out.println("PIA-nuevoThread?-3");
			pia.launch(
				bl,
				"localhost/"+"wikipediadumps", 
				"root", "root", 
				"localhost/"+p.getDatabase(), 
				p.getDbuser(), p.getDbpass(), 
				scene.piaMinLimit, scene.piaMaxLimit, 
				scene.piaIterationsLimit, 
				"sc"+scene.id+"_dbr", 
				scene.piaClean
				);
			System.out.println("PIA-nuevoThread?-4");
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
		System.out.println("en stopped!");
		this.setStopped()
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
