package webbluefinder



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Scene)
class SceneTests {
	
	void testFromType() {
		String f1 = 'Place'
		def testInstances = [f1]
		mockDomain(Scene,testInstances)
		def s = new Scene()
		s.setFromType(f1)
		assert s.getFromType() == f1
	}
	
	void testCreateNew() {
		String from = 'Place'
		String to = 'Person'
		String property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene()
		assert s.getFromType() == null
		assert s.getToType() == null
		assert s.getProperty() == null
		s.setFromType(from)
		s.setToType(to)
		s.setProperty(property)
		assert s.getFromType() == from
		assert s.getToType() == to
		assert s.getProperty() == property
		assertFalse s.isComplete()
		assertFalse s.isProcessing()
		assertFalse s.isCanceled()
		assert s.getErrors() == null
		assert s.getPercent() == 0
		assert s.getProcessName() == null
	}
	
	void testStart() {
		String from = 'Place'
		String to = 'Person'
		String property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene()
		s.setFromType(from)
		s.setToType(to)
		s.setProperty(property)
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
		String from = 'Place'
		String to = 'Person'
		String property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene()
		s.setFromType(from)
		s.setToType(to)
		s.setProperty(property)
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
		String from = 'Place'
		String to = 'Person'
		String property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene()
		s.setFromType(from)
		s.setToType(to)
		s.setProperty(property)
		s.start()
		assert s.getProcessName() == "DBRetrieverWrapper"
		s.next()
		assert s.getProcessName() == "PIAWrapper"
		s.next()
		assert s.getProcessName() == "BFWrapper"
		
	}
	
	void testIsComplete() {
		String from = 'Place'
		String to = 'Person'
		String property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene()
		s.setFromType(from)
		s.setToType(to)
		s.setProperty(property)
		s.start()
		assertFalse s.isComplete()
		s.next()
		assertFalse s.isComplete()
		s.next()
		assertFalse s.isComplete()
	}
	
	void testIsCanceled() {
       fail "Implement me"
	}
	
	void testIsProcessing() {
		String from = 'Place'
		String to = 'Person'
		String property = 'birthPlace'
		def testInstances = [from, to, property]
		mockDomain(Scene,testInstances)
		def s = new Scene()
		s.setFromType(from)
		s.setToType(to)
		s.setProperty(property)
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
       fail "Implement me"
	}
		
	void testGetErrors() {
       fail "Implement me"
	}
		
	void testExecuteQuery() {
       fail "Implement me"
	}
}
