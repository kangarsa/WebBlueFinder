package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(ObservableProcessController)
@Mock(ObservableProcess)
class ObservableProcessControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/observableProcess/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.observableProcessInstanceList.size() == 0
        assert model.observableProcessInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.observableProcessInstance != null
    }

    void testSave() {
        controller.save()

        assert model.observableProcessInstance != null
        assert view == '/observableProcess/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/observableProcess/show/1'
        assert controller.flash.message != null
        assert ObservableProcess.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/observableProcess/list'

        populateValidParams(params)
        def observableProcess = new ObservableProcess(params)

        assert observableProcess.save() != null

        params.id = observableProcess.id

        def model = controller.show()

        assert model.observableProcessInstance == observableProcess
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/observableProcess/list'

        populateValidParams(params)
        def observableProcess = new ObservableProcess(params)

        assert observableProcess.save() != null

        params.id = observableProcess.id

        def model = controller.edit()

        assert model.observableProcessInstance == observableProcess
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/observableProcess/list'

        response.reset()

        populateValidParams(params)
        def observableProcess = new ObservableProcess(params)

        assert observableProcess.save() != null

        // test invalid parameters in update
        params.id = observableProcess.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/observableProcess/edit"
        assert model.observableProcessInstance != null

        observableProcess.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/observableProcess/show/$observableProcess.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        observableProcess.clearErrors()

        populateValidParams(params)
        params.id = observableProcess.id
        params.version = -1
        controller.update()

        assert view == "/observableProcess/edit"
        assert model.observableProcessInstance != null
        assert model.observableProcessInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/observableProcess/list'

        response.reset()

        populateValidParams(params)
        def observableProcess = new ObservableProcess(params)

        assert observableProcess.save() != null
        assert ObservableProcess.count() == 1

        params.id = observableProcess.id

        controller.delete()

        assert ObservableProcess.count() == 0
        assert ObservableProcess.get(observableProcess.id) == null
        assert response.redirectedUrl == '/observableProcess/list'
    }
}
