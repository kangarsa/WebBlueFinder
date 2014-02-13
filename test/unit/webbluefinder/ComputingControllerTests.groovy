package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(ComputingController)
@Mock(Computing)
class ComputingControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/computing/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.computingInstanceList.size() == 0
        assert model.computingInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.computingInstance != null
    }

    void testSave() {
        controller.save()

        assert model.computingInstance != null
        assert view == '/computing/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/computing/show/1'
        assert controller.flash.message != null
        assert Computing.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/computing/list'

        populateValidParams(params)
        def computing = new Computing(params)

        assert computing.save() != null

        params.id = computing.id

        def model = controller.show()

        assert model.computingInstance == computing
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/computing/list'

        populateValidParams(params)
        def computing = new Computing(params)

        assert computing.save() != null

        params.id = computing.id

        def model = controller.edit()

        assert model.computingInstance == computing
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/computing/list'

        response.reset()

        populateValidParams(params)
        def computing = new Computing(params)

        assert computing.save() != null

        // test invalid parameters in update
        params.id = computing.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/computing/edit"
        assert model.computingInstance != null

        computing.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/computing/show/$computing.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        computing.clearErrors()

        populateValidParams(params)
        params.id = computing.id
        params.version = -1
        controller.update()

        assert view == "/computing/edit"
        assert model.computingInstance != null
        assert model.computingInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/computing/list'

        response.reset()

        populateValidParams(params)
        def computing = new Computing(params)

        assert computing.save() != null
        assert Computing.count() == 1

        params.id = computing.id

        controller.delete()

        assert Computing.count() == 0
        assert Computing.get(computing.id) == null
        assert response.redirectedUrl == '/computing/list'
    }
}
