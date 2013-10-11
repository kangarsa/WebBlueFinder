package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(AbstractProcessController)
@Mock(AbstractProcess)
class AbstractProcessControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        params["state"] = 'started'
    }

    void testIndex() {
        controller.index()
        assert "/abstractProcess/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.abstractProcessInstanceList.size() == 0
        assert model.abstractProcessInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.abstractProcessInstance != null
    }

    void testSave() {
        controller.save()

        assert model.abstractProcessInstance != null
        assert view == '/abstractProcess/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/abstractProcess/show/1'
        assert controller.flash.message != null
        assert AbstractProcess.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/abstractProcess/list'

        populateValidParams(params)
        def abstractProcess = new AbstractProcess(params)

        assert abstractProcess.save() != null

        params.id = abstractProcess.id

        def model = controller.show()

        assert model.abstractProcessInstance == abstractProcess
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/abstractProcess/list'

        populateValidParams(params)
        def abstractProcess = new AbstractProcess(params)

        assert abstractProcess.save() != null

        params.id = abstractProcess.id

        def model = controller.edit()

        assert model.abstractProcessInstance == abstractProcess
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/abstractProcess/list'

        response.reset()

        populateValidParams(params)
        def abstractProcess = new AbstractProcess(params)

        assert abstractProcess.save() != null

        // test invalid parameters in update
        params.id = abstractProcess.id
        //TODO: add invalid values to params object
		params.state = 'continued'

        controller.update()

        assert view == "/abstractProcess/edit"
        assert model.abstractProcessInstance != null

        abstractProcess.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/abstractProcess/show/$abstractProcess.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        abstractProcess.clearErrors()

        populateValidParams(params)
        params.id = abstractProcess.id
        params.version = -1
        controller.update()

        assert view == "/abstractProcess/edit"
        assert model.abstractProcessInstance != null
        assert model.abstractProcessInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/abstractProcess/list'

        response.reset()

        populateValidParams(params)
        def abstractProcess = new AbstractProcess(params)

        assert abstractProcess.save() != null
        assert AbstractProcess.count() == 1

        params.id = abstractProcess.id

        controller.delete()

        assert AbstractProcess.count() == 0
        assert AbstractProcess.get(abstractProcess.id) == null
        assert response.redirectedUrl == '/abstractProcess/list'
    }
}
