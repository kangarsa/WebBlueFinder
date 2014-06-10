package webbluefinder

import bflaunchers.TableExporterLauncher
import wbflisteners.ObservableProcess;
import wbflisteners.ProcessesListener;
	
class BlueFinderImporter extends Process implements ProcessesListener {
	String dbFrom
	String dbTo
	boolean overwrite

	static constraints = {
	}
	
	BlueFinderImporter(Scene s){
		super(s)
	}
	
	def getName() {
		return 'BlueFinderImporter'
	}
	
	def setDefaultDbfrom(){
		Properties p = Properties.getLast()
		dbFrom = p.dbImportedPIA
	}
	
	def setDefaultDbto(){
		Properties p = Properties.getLast()
		dbTo = p.database
	}
	
	def start() {
		//do execute
		Properties p = Properties.getLast()
		def bfes = scene.getBFEvaluationWrappers()
		def pfs = scene.getBFPathFinderWrappers()
		runAsync {
			System.out.println(dbFrom)
			if (!dbFrom) {
				setDefaultDbfrom()
			}
			if (!dbTo) {
				setDefaultDbto()
			}
			def ter = new TableExporterLauncher(p.dbUserImportedPIA, p.dbPassImportedPIA, dbFrom, dbTo, overwrite)
			ter.addObserver(this)
			//TODO No se tiene que poder ejecutar si hay otros en ejecución
			//BFEvaluation tables:
			for (proc in bfes) {
				def table = "sc"+scene.id+"_bfe"+proc.id
				ter.launch(table, table)
			}
			ter.launch("generalStatistics", "sc"+scene.id+"_generalStatistics")
			ter.launch("particularStatistics", "sc"+scene.id+"_particularStatistics")
			//BFPathFinder:
			for (proc in pfs) {
				def table = "sc"+scene.id+"_bfpf"+proc.id
				ter.launch(table, table)
			}
			ter.launch("UxV", "sc"+scene.id+"_UxV")
			ter.launch("V_Normalized", "sc"+scene.id+"_V_Normalized")
			ter.launch("U_from", "sc"+scene.id+"_U_from")
			ter.launch("U_page", "sc"+scene.id+"_U_page")
			ter.launch("NFPC", "sc"+scene.id+"_NFPC")
			ter.launch("U_pageEnhanced", "sc"+scene.id+"_U_pageEnhanced")
			finalized(ter)
		}
		System.out.println("BFI.poststart()")
	}
	
	def getNextProcess() {
		return this
	}
	
	def getProcessStep() {
		return 3
	}
	def isBlueFinderImporter() {
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
