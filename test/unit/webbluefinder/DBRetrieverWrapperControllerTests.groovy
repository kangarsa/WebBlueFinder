package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(DBRetrieverWrapperController)
@Mock(DBRetrieverWrapper)
class DBRetrieverWrapperControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/DBRetrieverWrapper/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.DBRetrieverWrapperInstanceList.size() == 0
        assert model.DBRetrieverWrapperInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.DBRetrieverWrapperInstance != null
    }

    void testSave() {
        controller.save()

        assert model.DBRetrieverWrapperInstance != null
        assert view == '/DBRetrieverWrapper/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/DBRetrieverWrapper/show/1'
        assert controller.flash.message != null
        assert DBRetrieverWrapper.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/DBRetrieverWrapper/list'

        populateValidParams(params)
        def DBRetrieverWrapper = new DBRetrieverWrapper(params)

        assert DBRetrieverWrapper.save() != null

        params.id = DBRetrieverWrapper.id

        def model = controller.show()

        assert model.DBRetrieverWrapperInstance == DBRetrieverWrapper
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/DBRetrieverWrapper/list'

        populateValidParams(params)
        def DBRetrieverWrapper = new DBRetrieverWrapper(params)

        assert DBRetrieverWrapper.save() != null

        params.id = DBRetrieverWrapper.id

        def model = controller.edit()

        assert model.DBRetrieverWrapperInstance == DBRetrieverWrapper
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/DBRetrieverWrapper/list'

        response.reset()

        populateValidParams(params)
        def DBRetrieverWrapper = new DBRetrieverWrapper(params)

        assert DBRetrieverWrapper.save() != null

        // test invalid parameters in update
        params.id = DBRetrieverWrapper.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/DBRetrieverWrapper/edit"
        assert model.DBRetrieverWrapperInstance != null

        DBRetrieverWrapper.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/DBRetrieverWrapper/show/$DBRetrieverWrapper.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        DBRetrieverWrapper.clearErrors()

        populateValidParams(params)
        params.id = DBRetrieverWrapper.id
        params.version = -1
        controller.update()

        assert view == "/DBRetrieverWrapper/edit"
        assert model.DBRetrieverWrapperInstance != null
        assert model.DBRetrieverWrapperInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/DBRetrieverWrapper/list'

        response.reset()

        populateValidParams(params)
        def DBRetrieverWrapper = new DBRetrieverWrapper(params)

        assert DBRetrieverWrapper.save() != null
        assert DBRetrieverWrapper.count() == 1

        params.id = DBRetrieverWrapper.id

        controller.delete()

        assert DBRetrieverWrapper.count() == 0
        assert DBRetrieverWrapper.get(DBRetrieverWrapper.id) == null
        assert response.redirectedUrl == '/DBRetrieverWrapper/list'
    }
}
