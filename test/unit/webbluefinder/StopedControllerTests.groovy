package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(StopedController)
@Mock(Stoped)
class StopedControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stoped/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stopedInstanceList.size() == 0
        assert model.stopedInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stopedInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stopedInstance != null
        assert view == '/stoped/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stoped/show/1'
        assert controller.flash.message != null
        assert Stoped.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stoped/list'

        populateValidParams(params)
        def stoped = new Stoped(params)

        assert stoped.save() != null

        params.id = stoped.id

        def model = controller.show()

        assert model.stopedInstance == stoped
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stoped/list'

        populateValidParams(params)
        def stoped = new Stoped(params)

        assert stoped.save() != null

        params.id = stoped.id

        def model = controller.edit()

        assert model.stopedInstance == stoped
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stoped/list'

        response.reset()

        populateValidParams(params)
        def stoped = new Stoped(params)

        assert stoped.save() != null

        // test invalid parameters in update
        params.id = stoped.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stoped/edit"
        assert model.stopedInstance != null

        stoped.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stoped/show/$stoped.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stoped.clearErrors()

        populateValidParams(params)
        params.id = stoped.id
        params.version = -1
        controller.update()

        assert view == "/stoped/edit"
        assert model.stopedInstance != null
        assert model.stopedInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stoped/list'

        response.reset()

        populateValidParams(params)
        def stoped = new Stoped(params)

        assert stoped.save() != null
        assert Stoped.count() == 1

        params.id = stoped.id

        controller.delete()

        assert Stoped.count() == 0
        assert Stoped.get(stoped.id) == null
        assert response.redirectedUrl == '/stoped/list'
    }
}
