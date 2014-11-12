package webbluefinder



import org.junit.*

import webbluefinder.process.PIAWrapper;
import grails.test.mixin.*

@TestFor(PIAWrapperController)
@Mock(PIAWrapper)
class PIAWrapperControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/PIAWrapper/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.PIAWrapperInstanceList.size() == 0
        assert model.PIAWrapperInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.PIAWrapperInstance != null
    }

    void testSave() {
        controller.save()

        assert model.PIAWrapperInstance != null
        assert view == '/PIAWrapper/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/PIAWrapper/show/1'
        assert controller.flash.message != null
        assert PIAWrapper.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/PIAWrapper/list'

        populateValidParams(params)
        def PIAWrapper = new PIAWrapper(params)

        assert PIAWrapper.save() != null

        params.id = PIAWrapper.id

        def model = controller.show()

        assert model.PIAWrapperInstance == PIAWrapper
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/PIAWrapper/list'

        populateValidParams(params)
        def PIAWrapper = new PIAWrapper(params)

        assert PIAWrapper.save() != null

        params.id = PIAWrapper.id

        def model = controller.edit()

        assert model.PIAWrapperInstance == PIAWrapper
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/PIAWrapper/list'

        response.reset()

        populateValidParams(params)
        def PIAWrapper = new PIAWrapper(params)

        assert PIAWrapper.save() != null

        // test invalid parameters in update
        params.id = PIAWrapper.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/PIAWrapper/edit"
        assert model.PIAWrapperInstance != null

        PIAWrapper.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/PIAWrapper/show/$PIAWrapper.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        PIAWrapper.clearErrors()

        populateValidParams(params)
        params.id = PIAWrapper.id
        params.version = -1
        controller.update()

        assert view == "/PIAWrapper/edit"
        assert model.PIAWrapperInstance != null
        assert model.PIAWrapperInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/PIAWrapper/list'

        response.reset()

        populateValidParams(params)
        def PIAWrapper = new PIAWrapper(params)

        assert PIAWrapper.save() != null
        assert PIAWrapper.count() == 1

        params.id = PIAWrapper.id

        controller.delete()

        assert PIAWrapper.count() == 0
        assert PIAWrapper.get(PIAWrapper.id) == null
        assert response.redirectedUrl == '/PIAWrapper/list'
    }
}
