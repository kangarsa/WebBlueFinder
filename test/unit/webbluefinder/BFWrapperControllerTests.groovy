package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(BFWrapperController)
@Mock(BFWrapper)
class BFWrapperControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/BFWrapper/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.BFWrapperInstanceList.size() == 0
        assert model.BFWrapperInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.BFWrapperInstance != null
    }

    void testSave() {
        controller.save()

        assert model.BFWrapperInstance != null
        assert view == '/BFWrapper/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/BFWrapper/show/1'
        assert controller.flash.message != null
        assert BFWrapper.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/BFWrapper/list'

        populateValidParams(params)
        def BFWrapper = new BFWrapper(params)

        assert BFWrapper.save() != null

        params.id = BFWrapper.id

        def model = controller.show()

        assert model.BFWrapperInstance == BFWrapper
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/BFWrapper/list'

        populateValidParams(params)
        def BFWrapper = new BFWrapper(params)

        assert BFWrapper.save() != null

        params.id = BFWrapper.id

        def model = controller.edit()

        assert model.BFWrapperInstance == BFWrapper
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/BFWrapper/list'

        response.reset()

        populateValidParams(params)
        def BFWrapper = new BFWrapper(params)

        assert BFWrapper.save() != null

        // test invalid parameters in update
        params.id = BFWrapper.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/BFWrapper/edit"
        assert model.BFWrapperInstance != null

        BFWrapper.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/BFWrapper/show/$BFWrapper.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        BFWrapper.clearErrors()

        populateValidParams(params)
        params.id = BFWrapper.id
        params.version = -1
        controller.update()

        assert view == "/BFWrapper/edit"
        assert model.BFWrapperInstance != null
        assert model.BFWrapperInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/BFWrapper/list'

        response.reset()

        populateValidParams(params)
        def BFWrapper = new BFWrapper(params)

        assert BFWrapper.save() != null
        assert BFWrapper.count() == 1

        params.id = BFWrapper.id

        controller.delete()

        assert BFWrapper.count() == 0
        assert BFWrapper.get(BFWrapper.id) == null
        assert response.redirectedUrl == '/BFWrapper/list'
    }
}
