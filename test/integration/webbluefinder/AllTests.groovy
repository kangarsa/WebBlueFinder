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

	@Test
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
	
	@Test
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
	
	@Test
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
	
//	@Test
	void testGetProcessName() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		assertEquals 'DBRetrieverWrapper', dbr.getName()
		def PIAWrapper pia = new DBRetrieverWrapper()
		assertEquals 'PIAWrapper', pia.getName()
		def BFRecommenderWrapper bfr = new BFRecommenderWrapper()
		assertEquals 'BFRecommenderWrapper', bfr.getName()
		def BFEvaluationWrapper bfe = new BFEvaluationWrapper()
		assertEquals 'BFRecommenderWrapper', bfr.getName()
		/*
		def BFPathFinderWrapperbfpf = new BFPathFinderWrapper()
		assertEquals 'BFRecommenderWrapper', bfr.getName()
		*/
		
	}
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
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
	
	@Test
	void testGetEmptyErrors() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
	//	mockDomain(Scene,testInstances)
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		assertEquals '', s.getSceneErrors()
	}
	
	@Test
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
	
	@Test
	void testExecuteQuery() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def DBRetrieverWrapper dbr = new DBRetrieverWrapper()
		def s = new Scene(from,to,property,dbr)
		s.start()
	}
}
