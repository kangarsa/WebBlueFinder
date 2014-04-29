package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(BFRecommenderWrapperController)
@Mock(BFRecommenderWrapper)
class BFRecommenderWrapperControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/BFRecommenderWrapper/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.BFRecommenderWrapperInstanceList.size() == 0
        assert model.BFRecommenderWrapperInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.BFRecommenderWrapperInstance != null
    }

    void testSave() {
        controller.save()

        assert model.BFRecommenderWrapperInstance != null
        assert view == '/BFRecommenderWrapper/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/BFRecommenderWrapper/show/1'
        assert controller.flash.message != null
        assert BFRecommenderWrapper.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/BFRecommenderWrapper/list'

        populateValidParams(params)
        def BFRecommenderWrapper = new BFRecommenderWrapper(params)

        assert BFRecommenderWrapper.save() != null

        params.id = BFRecommenderWrapper.id

        def model = controller.show()

        assert model.BFRecommenderWrapperInstance == BFRecommenderWrapper
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/BFRecommenderWrapper/list'

        populateValidParams(params)
        def BFRecommenderWrapper = new BFRecommenderWrapper(params)

        assert BFRecommenderWrapper.save() != null

        params.id = BFRecommenderWrapper.id

        def model = controller.edit()

        assert model.BFRecommenderWrapperInstance == BFRecommenderWrapper
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/BFRecommenderWrapper/list'

        response.reset()

        populateValidParams(params)
        def BFRecommenderWrapper = new BFRecommenderWrapper(params)

        assert BFRecommenderWrapper.save() != null

        // test invalid parameters in update
        params.id = BFRecommenderWrapper.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/BFRecommenderWrapper/edit"
        assert model.BFRecommenderWrapperInstance != null

        BFRecommenderWrapper.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/BFRecommenderWrapper/show/$BFRecommenderWrapper.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        BFRecommenderWrapper.clearErrors()

        populateValidParams(params)
        params.id = BFRecommenderWrapper.id
        params.version = -1
        controller.update()

        assert view == "/BFRecommenderWrapper/edit"
        assert model.BFRecommenderWrapperInstance != null
        assert model.BFRecommenderWrapperInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/BFRecommenderWrapper/list'

        response.reset()

        populateValidParams(params)
        def BFRecommenderWrapper = new BFRecommenderWrapper(params)

        assert BFRecommenderWrapper.save() != null
        assert BFRecommenderWrapper.count() == 1

        params.id = BFRecommenderWrapper.id

        controller.delete()

        assert BFRecommenderWrapper.count() == 0
        assert BFRecommenderWrapper.get(BFRecommenderWrapper.id) == null
        assert response.redirectedUrl == '/BFRecommenderWrapper/list'
    }
}
