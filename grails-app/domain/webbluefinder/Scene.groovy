package webbluefinder

import java.util.Arrays
import java.util.Observable;

//class Scene implements IProcessObserver {
class Scene{
String fromType
    String toType
    String property
	boolean queryable
//	AbstractProcess process
	int processStep
	String sceneErrors
/**
    static constraints = {
		fromType blank:false
	    property blank:false
	    toType blank:false
		queryable default:false, editable:false
		process editable:false, display:false
		processStep range:0..3, display:false
		sceneErrors editable:false
    }
	
	Scene(){
		this.process = new DBRetrieverWrapper()
		this.processStep = 0
		this.sceneErrors = ''
	}
	
	Scene(String from,String to,String property){
		this.fromType = from
		this.toType = to
		this.property = property
		this.process = new DBRetrieverWrapper()
		this.processStep = 0
		this.sceneErrors = ''
	}	
//	def processes = ["DBRetrieverWrapper","PiaWrapper","BFWrapper"]
	def totalProcesses = 3
	
	def isComplete() {
		if(process == null){			
			return false
		}
		return totalProcesses == processStep && process.isFinalized()
	}
	
	def isProcessing() {
		if(process == null){			
			return false
		}
		return processStep > 0 && process.isComputing()
	}
	
	def isCanceled() {
		return processStep > 0 && ((process == null) || process.hasProcessErrors()) 
	}
	
	def hasSceneErrors() {
		return sceneErrors != null
	}
	
	def getPercent() {
		if (processStep == 0) {
			return 0
		}
		return Math.floor(( ((process.totalSteps() * (processStep-1)) + process.getStep()) / ((totalProcesses * process.totalSteps())-1) )*100)
	}
		
	def executeQuery(from, to, property) {
		if (!canExecuteQuery()) {
			return null
		}
		def result = Scene.findAllByFromTypeAndToTypeAndProperty(from, to, property)
		return result
	}
	
	def canExecuteQuery() {
		return getPercent() == 100
	}
	
	def start() {
		if ((processStep == 0) && (fromType != null) && (toType != null) && (property != null)) {
			if (process == null) {
				process = new DBRetrieverWrapper()
			}
			process.execute()
			processStep = 1
			return processStep
		}
		return -1
	}
	
	def next() {
		if ((process != null) && process.hasProcessErrors()) {
			return -1
		}
		if (processStep == 0) {
			processStep = 1
			process = new DBRetrieverWrapper()
		} else if (processStep == 1) {
			processStep = 2
			process = new PIAWrapper()			
		} else if (processStep == 2) {
			processStep = 3
			process = new BFWrapper()
		}
		process.execute()
		return processStep
	}
	
	def getProcessName() {
		if (process == null) {
			return null
		}
		return process.getName()
	}
	
	def cancel() {
		process == null
	}
	
	@Override
	public void updateProcess() {
		if (!process.hasProcessErrors() && process.isFinalized()) {
			this.next()
		}
	}
	
	@Override
	public void updateErrors() {
		this.setSceneErrors(this.getSceneErrors()+"<< Error in "+process.getName()+": "+ process.getErrors()+" >> ")
	}
**/
}
