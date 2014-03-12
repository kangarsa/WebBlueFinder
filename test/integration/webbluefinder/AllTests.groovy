package webbluefinder

import static org.junit.Assert.*
import org.junit.*

class AllTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
	void testFromType() {
		def from = 'Place'
		def s = new Scene(fromType: from)
		assertEquals from, s.getFromType()
	}
	
	void testCreateNew() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(fromType:from, toType:to, property:property, process:dbr)
		assertEquals from, s.getFromType()
		assertEquals to, s.getToType()
		assertEquals property, s.getProperty()
		assertFalse s.isComplete()
		assertFalse s.isProcessing()
		assertFalse s.isCanceled()
		assertEquals '', s.getSceneErrors()
		assertEquals 0, s.getPercent(), 0
		assertEquals 'DBRetrieverWrapper', s.getProcessName()
	}
	
	void testStart() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		assertEquals 1, s.start()
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		//assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assertEquals 'DBRetrieverWrapper', s.getProcessName()
		assertEquals 12, s.getPercent(), 0
		s.start()
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		//assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assertEquals 'DBRetrieverWrapper', s.getProcessName()
		assertEquals 12, s.getPercent(), 0
	}
	
	void testNext() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		assertEquals 1, s.start()
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assertEquals 'DBRetrieverWrapper', s.getProcessName()
		assertEquals 12, s.getPercent(), 0
		assertEquals 2, s.nextProcess()
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assertEquals 'PIAWrapper', s.getProcessName()
		assertEquals 50, s.getPercent(), 0
		assertEquals 3, s.nextProcess()
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assertEquals 'BFWrapper', s.getProcessName()
		assertEquals 87, s.getPercent(), 0
		assertEquals 3, s.nextProcess()
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assertEquals 'BFWrapper', s.getProcessName()
		assertEquals 87, s.getPercent(), 0
	}
	
	void testGetProcessName() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		s.start()
		assertEquals 'DBRetrieverWrapper', s.getProcessName()
		s.nextProcessState()
		s.nextProcessState()
		s.nextProcess()
		assertEquals 'PIAWrapper', s.getProcessName()
		s.nextProcessState()
		s.nextProcessState()
		s.nextProcess()
		assertEquals 'BFWrapper', s.getProcessName()
	}
	
	void testGetNames() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		assertEquals 'Not Started', s.getProcessStateName()
		s.start()
		assertEquals 'DBRetrieverWrapper', s.getProcessName()
		assertEquals 'Computing', s.getProcessStateName()
		s.nextProcessState()
		assertEquals 'Finalized', s.getProcessStateName()
		s.nextProcess()
		assertEquals 'PIAWrapper', s.getProcessName()
		assertEquals 'Computing', s.getProcessStateName()
		s.nextProcessState()
		assertEquals 'Finalized', s.getProcessStateName()
		s.nextProcess()
		assertEquals 'BFWrapper', s.getProcessName() 
		assertEquals 'Computing', s.getProcessStateName()
		s.nextProcessState()
		assertEquals  'Finalized', s.getProcessStateName()
	}
	
	void testIsComplete() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		s.start()
		assertFalse s.isComplete()
		s.nextProcess()
		assertFalse s.isComplete()
		s.nextProcess()
		assertFalse s.isComplete()
	}
	
	void testIsCanceled() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		assert s.start() == 1
		assertTrue s.isProcessing()
		s.stop()
		assertTrue s.isCanceled()
	}
	
	void testIsProcessing() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		assertEquals 1, s.start()
		assertTrue s.isProcessing()
		s.nextProcessState()
		s.nextProcessState()
		assertEquals 2, s.nextProcess()
		assertTrue s.isProcessing()
		s.nextProcessState()
		s.nextProcessState()
		assertEquals 3, s.nextProcess()
		assertTrue s.isProcessing()
		s.nextProcessState()
		s.nextProcessState()
		assertEquals 3, s.nextProcess()
		assertTrue s.isComplete()
	}
	
	void testGetPercent() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		assertEquals 0, s.getPercent(), 0
		assertEquals 1, s.start()
		assertEquals 12, s.getPercent(), 0
		assertEquals 2, s.nextProcess()
		assertEquals 50, s.getPercent(), 0
		assertEquals 3, s.nextProcess()
		assertEquals 87, s.getPercent(), 0
		assertEquals 3, s.nextProcess()
		assertEquals 87, s.getPercent(), 0
	}
	
	void testGetErrors() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
	//	mockDomain(Scene,testInstances)
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		assertEquals '', s.getSceneErrors()
		assertEquals 1, s.start()
		assertEquals '', s.getSceneErrors()
	}
	
	void testPreviousProcess(){
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		s.start()
		def p = s.getProcess()
//		println('es:: '+s.previousProcess.find{it.id == p.getId()})
		assertEquals null, s.previousProcess?.contains(p)
		assertEquals p, s.getProcess()
		s.nextProcess()
		assertTrue s.previousProcess.contains(p)
		assertFalse p == s.getProcess()
	}
	
	void testExecuteQuery() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		s.start()
	}
}
