package webbluefinder

import java.util.Arrays

//import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

class Scene {
	String name
	String fromType
    String toType
    String property
	Process process
	int piaMinLimit = 1
	int piaMaxLimit = 600
	int piaIterationsLimit = 2
	boolean piaClean = true
	
	static hasMany = [previousProcess: Process]
	
	static mapping = {
		process cascade: "all"
		previousProcess sort: "id", order: "desc"
	}

    static constraints = {
		name blank:false, display:true, unique:true
		fromType blank:false
	    property blank:false
	    toType blank:false
		process nullable:true, editable:false, display:true
		//previousProcess editable:false, display:true
		piaMinLimit min:1
		piaMaxLimit min:1
		piaIterationsLimit min:1
    }
	
	Scene(String from,String to,String prop,Process dbr){
		this()
		fromType = from
		toType = to
		property = prop
		process = dbr
		dbr.setScene(this)
	}

//	def processes = ["DBRetrieverWrapper","PiaWrapper","BFWrapper"]
	def totalProcesses = 3
	
	def getDBRetrieverWrappers(){
		return this.previousProcess.findAll{ p -> p.isDBRetrieverWrapper() }
	}	
	def getPIAWrappers(){
		return this.previousProcess.findAll{ p -> p.isPIAWrapper() }
	}
	def getBFEvaluationWrappers(){
		return this.previousProcess.findAll{ p -> p.isBFEvaluationWrapper() }
	}
	def getBFRecommenderWrappers(){
		return this.previousProcess.findAll{ p -> p.isBFRecommenderWrapper() }
	}
	def getBFPathFinderWrappers(){
		return this.previousProcess.findAll{ p -> p.isBFPathFinderWrapper() }
	}
	def getBlueFinderImporters(){
		return this.previousProcess.findAll{ p -> p.isBlueFinderImporter() }
	}
	
	def addNewPIA(){
		return previousProcess.add(new PIAWrapper(this))
	}
	def addNewDBRetriever(){
		return previousProcess.add(new DBRetrieverWrapper(this))
	}
	def addNewBFPathFinder(){
		//return previousProcess.add(new BFPathFinderWrapper(this))
	}
	def addNewBFEvaluation(){
		return previousProcess.add(new BFEvaluationWrapper(this))
	}
	def addNewBFRecommender(){
		return previousProcess.add(new BFRecommenderWrapper(this))
	}
	def addNewDumpLoader(String dumpPath){
		return previousProcess.add(new DumpLoader(this, dumpPath))
	}
	
	def setNewProcess(Process p) {
		if(process != null) {
			this.addToPreviousProcess(process)
		}
		process = p
	}
	
	def getSceneErrors() {
		def e = ''
		previousProcess.each { p -> 
			if(p.hasProcessErrors()) {
				e += p.getProcessName() + ' | '
				e += p.getStateName() + ' | '
				e += p.getProcessErrors() + ' || '
			}
		}
		if(process.hasProcessErrors()) {
			e += process.getProcessName() + ' | '
			e += process.getStateName() + ' | '
			e += process.getProcessErrors() + ' || '
		}
		return e
	}
	
	def processStep() {
		return process.getProcessStep()
	}
	
	def getProcessStateName() {
		return process.getStateName()
	}
	
	def isComplete() {
		if(process != null) {			
			return (!process.hasNextProcess()) && process.isFinalized()
		}
		return false
	}
	
	def isProcessing() {
		if(process != null) {	
			return process.isComputing()
		}
		return false
	}
	
	def isComputing() {
		if(process != null) {	
			return process.isComputing()
		}
		return false
	}
	
	def isCanceled() {
		return process.isStoped()
	}
	
	def hasSceneErrors() {
		return process.getProcessErrors() == '' || previousProcess.getProcessErrors().count('') > 0
	}
	
	def getPercent() {
		if (this.processStep() <= 0) {
			return 0
		}
		return Math.floor(( ((process.totalSteps() * (this.processStep()-1)) + process.getStateStep()) / ((totalProcesses * process.totalSteps())-1) )*100)
	}
		
	def executeQuery(from, to, property) {
		if (!canExecuteQuery()) {
			return null
		}
		def result = Scene.findAllByFromTypeAndToTypeAndProperty(from, to, property)
		return result
	}
	
	def canExecuteQuery() {
		return process.isBFWrapper() && process.isFinalized()
	}
	
	def start() {
		return process.execute()
	}
	
	//HACER ANDAR ESTO??
	def nextProcess() {
		this.process.nextProcess()
		//this.save()
		return process.execute()
	}
	
	//HACER ANDAR ESTO??
	def forceNextProcess() {
		this.process.forceNextProcess()
		this.save()
		return process.execute()
	}
	
	def nextProcessState() {
		process.nextState()
		return getProcessStateName()
	}
	
	def getProcessName() {
		if (process) {
			return process.getName()
		}
		return ''
	}
	
	def stop() {
		process.setStoped()
	}
	
	def updateState() {
	}
	
	def updateProcessErrors() {
//		this.setSceneErrors(this.getSceneErrors()+"<< Error in "+process.getName()+": "+ process.getProcessErrors()+" >> ")
	}
	
	def updateFinalized() {
		if (!this.getProcess().hasProcessErrors() && process.isFinalized()) {
			this.nextProcess()
		}
	}
	
	def updateComputing() {
	}
	
	def updateStoped() {
	}
	
	def updateProcessState() {
		//processState = process.getProcessState()
	}

}
