package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(ProcessStateController)
@Mock(ProcessState)
class ProcessStateControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/processState/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.processStateInstanceList.size() == 0
        assert model.processStateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.processStateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.processStateInstance != null
        assert view == '/processState/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/processState/show/1'
        assert controller.flash.message != null
        assert ProcessState.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/processState/list'

        populateValidParams(params)
        def processState = new ProcessState(params)

        assert processState.save() != null

        params.id = processState.id

        def model = controller.show()

        assert model.processStateInstance == processState
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/processState/list'

        populateValidParams(params)
        def processState = new ProcessState(params)

        assert processState.save() != null

        params.id = processState.id

        def model = controller.edit()

        assert model.processStateInstance == processState
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/processState/list'

        response.reset()

        populateValidParams(params)
        def processState = new ProcessState(params)

        assert processState.save() != null

        // test invalid parameters in update
        params.id = processState.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/processState/edit"
        assert model.processStateInstance != null

        processState.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/processState/show/$processState.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        processState.clearErrors()

        populateValidParams(params)
        params.id = processState.id
        params.version = -1
        controller.update()

        assert view == "/processState/edit"
        assert model.processStateInstance != null
        assert model.processStateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/processState/list'

        response.reset()

        populateValidParams(params)
        def processState = new ProcessState(params)

        assert processState.save() != null
        assert ProcessState.count() == 1

        params.id = processState.id

        controller.delete()

        assert ProcessState.count() == 0
        assert ProcessState.get(processState.id) == null
        assert response.redirectedUrl == '/processState/list'
    }
}
