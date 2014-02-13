package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(FinalizedController)
@Mock(Finalized)
class FinalizedControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/finalized/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.finalizedInstanceList.size() == 0
        assert model.finalizedInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.finalizedInstance != null
    }

    void testSave() {
        controller.save()

        assert model.finalizedInstance != null
        assert view == '/finalized/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/finalized/show/1'
        assert controller.flash.message != null
        assert Finalized.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/finalized/list'

        populateValidParams(params)
        def finalized = new Finalized(params)

        assert finalized.save() != null

        params.id = finalized.id

        def model = controller.show()

        assert model.finalizedInstance == finalized
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/finalized/list'

        populateValidParams(params)
        def finalized = new Finalized(params)

        assert finalized.save() != null

        params.id = finalized.id

        def model = controller.edit()

        assert model.finalizedInstance == finalized
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/finalized/list'

        response.reset()

        populateValidParams(params)
        def finalized = new Finalized(params)

        assert finalized.save() != null

        // test invalid parameters in update
        params.id = finalized.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/finalized/edit"
        assert model.finalizedInstance != null

        finalized.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/finalized/show/$finalized.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        finalized.clearErrors()

        populateValidParams(params)
        params.id = finalized.id
        params.version = -1
        controller.update()

        assert view == "/finalized/edit"
        assert model.finalizedInstance != null
        assert model.finalizedInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/finalized/list'

        response.reset()

        populateValidParams(params)
        def finalized = new Finalized(params)

        assert finalized.save() != null
        assert Finalized.count() == 1

        params.id = finalized.id

        controller.delete()

        assert Finalized.count() == 0
        assert Finalized.get(finalized.id) == null
        assert response.redirectedUrl == '/finalized/list'
    }
}
