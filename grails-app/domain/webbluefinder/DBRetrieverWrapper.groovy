package webbluefinder

//import finder.*;
//import wbflisteners.DBRetrieverRunnable
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
		Runnable task = new DBRetrieverRunnable();
		Thread worker = new Thread(task);
//		// We can set the name of the thread
		worker.setName(this.scene.fromType + this.scene.toType);
//		// Start the thread, never call method run() direct
		System.out.println("worker.prestart()");
		worker.start();
		System.out.println("worker.poststart()");
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
		// TODO Auto-generated method stub
		
	}
}
