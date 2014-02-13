package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(NotStartedController)
@Mock(NotStarted)
class NotStartedControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
       	// params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/notStarted/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.notStartedInstanceList.size() == 0
        assert model.notStartedInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.notStartedInstance != null
    }

    void testSave() {
        controller.save()

        assert model.notStartedInstance != null
        assert view == '/notStarted/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/notStarted/show/1'
        assert controller.flash.message != null
        assert NotStarted.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/notStarted/list'

        populateValidParams(params)
        def notStarted = new NotStarted(params)

        assert notStarted.save() != null

        params.id = notStarted.id

        def model = controller.show()

        assert model.notStartedInstance == notStarted
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/notStarted/list'

        populateValidParams(params)
        def notStarted = new NotStarted(params)

        assert notStarted.save() != null

        params.id = notStarted.id

        def model = controller.edit()

        assert model.notStartedInstance == notStarted
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/notStarted/list'

        response.reset()

        populateValidParams(params)
        def notStarted = new NotStarted(params)

        assert notStarted.save() != null

        // test invalid parameters in update
        params.id = notStarted.id
        //TODO: add invalid values to params object
		params["data"] = 'a'

        controller.update()

        assert view == "/notStarted/edit"
        assert model.notStartedInstance != null

        notStarted.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/notStarted/show/$notStarted.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        notStarted.clearErrors()

        populateValidParams(params)
        params.id = notStarted.id
        params.version = -1
        controller.update()

        assert view == "/notStarted/edit"
        assert model.notStartedInstance != null
        assert model.notStartedInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/notStarted/list'

        response.reset()

        populateValidParams(params)
        def notStarted = new NotStarted(params)

        assert notStarted.save() != null
        assert NotStarted.count() == 1

        params.id = notStarted.id

        controller.delete()

        assert NotStarted.count() == 0
        assert NotStarted.get(notStarted.id) == null
        assert response.redirectedUrl == '/notStarted/list'
    }
}
