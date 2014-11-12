package webbluefinder.process

import bflaunchers.BFPathFinderLauncher
import wbflisteners.ObservableProcess;
import wbflisteners.ProcessesListener;
import webbluefinder.Properties;
import webbluefinder.Scene;

/**
 * BFPathFinderWrapper
 * A domain class describes the data object and it's mapping to the database
 */
class BFPathFinderWrapper extends Process implements ProcessesListener {

    static constraints = {
    }
	
	BFPathFinderWrapper(Scene s){
		super(s)
	}
	
	def getTableNamePiece() {
		return 'bfpf'+this.id;
	}
	
	def getName() {
		return 'BFPathFinderWrapper'
	}
	
	def start() {
		//do execute
		Properties p = Properties.getLast()
		System.out.println("workerBFPF.prestart()")
		runAsync {
			System.out.println("?BFPF-1")
			def bfpf = new BFPathFinderLauncher()
			System.out.println("?BFPF-2")
			bfpf.addObserver(this)
			
			System.out.println("?BFPF-3")
			bfpf.launch("localhost/"+"wikipediadumps",
			"root", "root",
			"localhost/"+p.getDatabase(),
			p.getDbuser(), p.getDbpass(),
			true,scene.name,3,0)
			System.out.println("?BFPF-4")
		}
		System.out.println("workerBFPF.poststart()")
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
		System.out.println("en stopped!");
		this.setStopped()
		
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
