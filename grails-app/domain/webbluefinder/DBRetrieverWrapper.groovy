package webbluefinder

import wbflisteners.DBRetrieverLauncher;
import wbflisteners.DBRetrieverRunnable
import wbflisteners.ObservableProcess
import wbflisteners.ProcessesListener


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
//		Runnable task = new DBRetrieverRunnable(this.scene.id,this.scene.fromType,this.scene.toType,this.scene.property,p.hostname,p.database,p.dbuser,p.dbpass)
//		task.addObserver(this)
//		Thread worker = new Thread(task)
//		// We can set the name of the thread
//		worker.setName(this.scene.name)
//		// Start the thread, never call method run() direct
		System.out.println("worker.prestart()")
		//worker.start()
		runAsync {
			def dbr = new DBRetrieverLauncher()
			def query = "SELECT ?from, ?to WHERE { ?from a <"+scene.fromType+">. ?to a <"+scene.toType+">. ?to <"+scene.property+"> ?from. }";
			dbr.addObserver(this)
			System.out.println(query);
			dbr.launch(p.hostname+"/"+p.database, query, p.dbuser, p.dbpass, scene.id+"_results");
		}
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
	def isPIAWrapper() {
		return false
	}
	def isBFWrapper() {
		return false
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
