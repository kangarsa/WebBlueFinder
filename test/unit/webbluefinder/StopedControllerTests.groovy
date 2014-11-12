package webbluefinder



import org.junit.*

import webbluefinder.state.Stopped;
import grails.test.mixin.*

@TestFor(StoppedController)
@Mock(Stopped)
class StoppedControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stopped/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stoppedInstanceList.size() == 0
        assert model.stoppedInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stoppedInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stoppedInstance != null
        assert view == '/stopped/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stopped/show/1'
        assert controller.flash.message != null
        assert Stopped.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stopped/list'

        populateValidParams(params)
        def stopped = new Stopped(params)

        assert stopped.save() != null

        params.id = stopped.id

        def model = controller.show()

        assert model.stoppedInstance == stopped
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stopped/list'

        populateValidParams(params)
        def stopped = new Stopped(params)

        assert stopped.save() != null

        params.id = stopped.id

        def model = controller.edit()

        assert model.stoppedInstance == stopped
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stopped/list'

        response.reset()

        populateValidParams(params)
        def stopped = new Stopped(params)

        assert stopped.save() != null

        // test invalid parameters in update
        params.id = stopped.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stopped/edit"
        assert model.stoppedInstance != null

        stopped.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stopped/show/$stopped.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stopped.clearErrors()

        populateValidParams(params)
        params.id = stopped.id
        params.version = -1
        controller.update()

        assert view == "/stopped/edit"
        assert model.stoppedInstance != null
        assert model.stoppedInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stopped/list'

        response.reset()

        populateValidParams(params)
        def stopped = new Stopped(params)

        assert stopped.save() != null
        assert Stopped.count() == 1

        params.id = stopped.id

        controller.delete()

        assert Stopped.count() == 0
        assert Stopped.get(stopped.id) == null
        assert response.redirectedUrl == '/stopped/list'
    }
}
