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
		System.out.println("BFI.prestart()")
		def bfes = scene.getBFEvaluationWrappers()
		def pfs = scene.getBFPathFinderWrappers()
		runAsync {
			if (!dbFrom) {
				setDefaultDbfrom()
			}
			if (!dbTo) {
				setDefaultDbto()
			}
			System.out.println("nuevoThread?BFI")
			def ter = new TableExporterLauncher(p.dbUserImportedPIA, p.dbPassImportedPIA, p.dbImportedPIA, dbTo, overwrite)
			System.out.println("nuevoThread?BFI2")
			ter.addObserver(this)
			System.out.println("nuevoThread?BFI3")
			//TODO No se tiene que poder ejecutar si hay otros en ejecución
			//BFEvaluation tables:
			System.out.println("nuevoThread?BFI3.1.1")
			for (proc in bfes) {
				System.out.println("nuevoThread?BFI3.1.2")
				def table = "sc"+scene.id+"_bfe"+proc.id
				System.out.println("nuevoThread?BFI3.1.3")
				ter.launch(table, table)
				System.out.println("nuevoThread?BFI3.1")
			}
			System.out.println("nuevoThread?BFI3.2.0")
			ter.launch("generalStatistics", "sc"+scene.id+"_generalStatistics")
			System.out.println("nuevoThread?BFI3.2")
			ter.launch("particularStatistics", "sc"+scene.id+"_particularStatistics")
			System.out.println("nuevoThread?BFI3.3")
			//BFPathFinder:
			for (proc in pfs) {
				def table = "sc"+scene.id+"_bfpf"+proc.id
				ter.launch(table, table)
				System.out.println("nuevoThread?BFI3.4")
			}
			
			System.out.println("nuevoThread?BFI4")
			ter.launch("UxV", "sc"+scene.id+"_UxV")
			System.out.println("nuevoThread?BFI5")
			ter.launch("V_Normalized", "sc"+scene.id+"_V_Normalized")
			System.out.println("nuevoThread?BFI6")
			ter.launch("U_from", "sc"+scene.id+"_U_from")
			System.out.println("nuevoThread?BFI7")
			ter.launch("NFPC", "sc"+scene.id+"_NFPC")
			System.out.println("nuevoThread?BFI8")
			ter.launch("U_pageEnhanced", "sc"+scene.id+"_U_pageEnhanced")
			System.out.println("nuevoThread?BFI9")
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
