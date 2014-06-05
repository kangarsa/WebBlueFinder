package webbluefinder

import bflaunchers.BFRecommenderLauncher
import wbflisteners.ObservableProcess
import wbflisteners.ProcessesListener

class BFRecommenderWrapper extends Process implements ProcessesListener {
	// Sin estos strings vacios no es capaz de guardar el Process en db
	String bfrFrom="Art_Institute_of_Chicago"
	String bfrTo="Kevin_O'Dwyer_(silversmith)"
	String db="localhost/results"
	int neighbour=5
	int maxRecommendations=100000

    static constraints = {
    }
	
	BFRecommenderWrapper(Scene s){
		super(s)
	}
	
	def getName() {
		return 'BFRecommenderWrapper'
	}
	
	def start() {
		//do execute
		Properties p = Properties.getLast()
		System.out.println("workerBFR.prestart()")
		runAsync {
			System.out.println("nuevoThread?BFR")
			def bfr = new BFRecommenderLauncher()
			System.out.println("nuevoThread?BFR2")
			bfr.addObserver(this)
			System.out.println("nuevoThread?BFR3")
			//bfr.launch("asd","dsa",5,10000,"results")
			bfr.launch(this.bfrFrom, this.bfrTo, this.neighbour, this.maxRecommendations, this.db)
			System.out.println("nuevoThread?BFR4")
		}
		System.out.println("workerBFR.poststart()")
	}
	
	def getNextProcess() {
		return this
	}
	
	def getProcessStep() {
		return 3
	}
	def isBFRecommenderWrapper() {
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
