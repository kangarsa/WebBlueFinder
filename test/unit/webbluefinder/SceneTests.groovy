package webbluefinder



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Scene)
class SceneTests {
	
	void testFromType() {
		def f1 = 'Place'
		def testInstances = [f1]
		mockDomain(Scene,testInstances)
		def s = new Scene()
		s.setFromType(f1)
		assert s.getFromType() == f1
	}
	
	void testCreateNew() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		assert s.getFromType() == from
		assert s.getToType() == to
		assert s.getProperty() == property
		assertFalse s.isComplete()
		assertFalse s.isProcessing()
		assertFalse s.isCanceled()
		assert s.getSceneErrors() == ''
		assert s.getPercent() == 0
		assert s.getProcessName() == 'DBRetrieverWrapper'
	}
	
	void testStart() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		assert s.start() == 1
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		//assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assert s.getProcessName() == "DBRetrieverWrapper"
		assert s.getPercent() == 12
		s.start()
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		//assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assert s.getProcessName() == "DBRetrieverWrapper"
		assert s.getPercent() == 12
	}
	
	void testNext() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		assert s.start() == 1
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		//assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assert s.getProcessName() == "DBRetrieverWrapper"
		assert s.getPercent() == 12
		assert s.next() == 2
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		//assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assert s.getProcessName() == "PIAWrapper"
		assert s.getPercent() == 50
		assert s.next() == 3
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		//assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assert s.getProcessName() == "BFWrapper"
		assert s.getPercent() == 87
		assert s.next() == 3
		assertFalse s.isComplete()
		assertTrue s.isProcessing()
		//assertFalse s.isCanceled()
		//assert s.getErrors() == null
		assert s.getProcessName() == "BFWrapper"
		assert s.getPercent() == 87
	}
	
	void testgetProcessName() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		s.start()
		assert s.getProcessName() == "DBRetrieverWrapper"
		s.next()
		assert s.getProcessName() == "PIAWrapper"
		s.next()
		assert s.getProcessName() == "BFWrapper"
		
	}
	
	void testIsComplete() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		s.start()
		assertFalse s.isComplete()
		s.next()
		assertFalse s.isComplete()
		s.next()
		assertFalse s.isComplete()
	}
	
	void testIsCanceled() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		assert s.start() == 1
		assertTrue s.isProcessing()
		s.cancel()
		assertTrue s.isCanceled()
	}
	
	void testIsProcessing() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		assert s.start() == 1
		assertTrue s.isProcessing()
		assert s.next() == 2
		assertTrue s.isProcessing()
		assert s.next() == 3
		assertTrue s.isProcessing()
		assert s.next() == 3
		assertTrue s.isProcessing()
	}
	
	void testGetPercent() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		assert s.getPercent() == 0
		assert s.start() == 1
		assert s.getPercent() == 12
		assert s.next() == 2
		assert s.getPercent() == 50
		assert s.next() == 3
		assert s.getPercent() == 87
		assert s.next() == 3
		assert s.getPercent() == 87
	}
		
	void testGetErrors() {
		def from = 'Place'
		def to = 'Person'
		def property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene(from,to,property)
		assert s.getSceneErrors() == ''
		assert s.start() == 1
		assert s.getSceneErrors() == ''
	}
		
	void testExecuteQuery() {
       fail "Implement me"
	}
}
