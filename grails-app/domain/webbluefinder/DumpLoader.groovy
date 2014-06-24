package webbluefinder

import bflaunchers.DumpLoaderLauncher
import wbflisteners.ObservableProcess;
import wbflisteners.ProcessesListener

class DumpLoader extends Process implements ProcessesListener {
	String dumpPath

    static constraints = {
    }
	
	DumpLoader(Scene s){
		super(s)
	}
	
	DumpLoader(Scene s, String dumpPath){
		super(s)
		this.dumpPath = dumpPath
	}
	
	def getName() {
		return 'DumpLoader'
	}
	
	def start() {
		if (dumpPath) {
			runAsync {
				Properties p = Properties.getLast()
				def dll = new DumpLoaderLauncher()
				dll.addObserver(this)
				dll.launch(dumpPath, p.dbImportedPIA/*+"_"+id*/, p.dbUserImportedPIA, p.dbPassImportedPIA, true)
			}
		}
	}
	
	def getNextProcess() {
		return new BFRecommenderWrapper(scene)
	}
	
	def getProcessStep() {
		return 2
	}
	def isDumpLoader() {
		return true
	}
	def hasNextProcess() {
		return true
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
		this.setFinalized()		
	}
}
