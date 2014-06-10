package webbluefinder

import bflaunchers.DBRetrieverLauncher;
import wbflisteners.ObservableProcess
import wbflisteners.ProcessesListener
//import static grails.async.Promises.*


class DBRetrieverWrapper extends Process implements ProcessesListener {

    static constraints = {
    }
	
	DBRetrieverWrapper(Scene s){
		super(s)
	}
	
	def getName() {
		return 'DBRetrieverWrapper'
	}
	
	def start() {
//do execute
		Properties p = Properties.getLast()
		System.out.println("worker.prestart()")
		
		def dbr = new DBRetrieverLauncher()
		def query = "SELECT ?from, ?to WHERE { ?from a <"+scene.fromType+">. ?to a <"+scene.toType+">. ?to <"+scene.property+"> ?from. }";
		dbr.addObserver(this)
		
		runAsync {
			//System.out.println(query);
			//System.out.println("Async Previo DBRetriever ");
			dbr.launch(p.hostname+"/"+p.database, query, p.dbuser, p.dbpass, "sc"+scene.id+"_dbr");
			//System.out.println("Async Post DBRetriever ");
			//return "repiola"
		}
		// block until result is called
		//def result = prom.get()
		// block for the specified time
		//def result = p.get(1,MINUTES)
		System.out.println("worker.poststart()")
	}
	
	def getNextProcess() {
		return new PIAWrapper(scene)
	}
	
	def getProcessStep() {
		return 1
	}
	def isDBRetrieverWrapper() {
		return true
	}
	def hasNextProcess() {
		return true
	}

	@Override
	public void computing(ObservableProcess dbr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopped(ObservableProcess dbr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notStarted(ObservableProcess dbr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalized(ObservableProcess dbr) {
		System.out.println("en finalized");
		this.setFinalized()
	}
}
