package webbluefinder

import java.util.Arrays

class Scene {
    String fromType
    String toType
    String property
	boolean queryable
	AbstractProcess process
	int processStep

    static constraints = {
	    fromType blank:false
	    toType blank:false
	    property blank:false
		queryable default:false
		process nullable:true
		processStep nullable:false, default:0
    }
	
//	def processes = ["DBRetrieverWrapper","PiaWrapper","BFWrapper"]
	def processesCount = 3
	
	def isComplete() {
		if(process == null){			
			return false
		}
		return processesCount == processStep && process.isFinalized()
	}
	
	def isProcessing() {
		if(process == null){			
			return false
		}
		return processStep > 0 && process.isComputing()
	}
	
	def isCanceled() {
		return processStep > 0 && (process == null || process.hasErrors()) 
	}
	
	def getPercent() {
		if (process == null) {
			return 0
		}
		return Math.floor((((process.getStep()+1) * processStep) / (processesCount * process.totalSteps()))*100)
	}
	
	def getErrors() {
		if (process != null) {
			return process.getErrors()
		}
		return null
	}
	
	def executeQuery(from, to, property) {
		
	}
	
	def canExecuteQuery() {
		return getPercent() == 100
	}
	
	def start() {
		if ((processStep == 0) && (fromType != null) && (toType != null) && (property != null)) {
			processStep = 1
			process = new DBRetrieverWrapper()
			process.execute()
			return processStep
		}
		return 0
	}
	
	def next() {
		if ((process != null) && (process.hasErrors())) {
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
}
