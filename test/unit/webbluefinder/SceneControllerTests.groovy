package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(SceneController)
@Mock(Scene)
class SceneControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/scene/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.sceneInstanceList.size() == 0
        assert model.sceneInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.sceneInstance != null
    }

    void testSave() {
        controller.save()

        assert model.sceneInstance != null
        assert view == '/scene/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/scene/show/1'
        assert controller.flash.message != null
        assert Scene.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/scene/list'

        populateValidParams(params)
        def scene = new Scene(params)

        assert scene.save() != null

        params.id = scene.id

        def model = controller.show()

        assert model.sceneInstance == scene
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/scene/list'

        populateValidParams(params)
        def scene = new Scene(params)

        assert scene.save() != null

        params.id = scene.id

        def model = controller.edit()

        assert model.sceneInstance == scene
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/scene/list'

        response.reset()

        populateValidParams(params)
        def scene = new Scene(params)

        assert scene.save() != null

        // test invalid parameters in update
        params.id = scene.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/scene/edit"
        assert model.sceneInstance != null

        scene.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/scene/show/$scene.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        scene.clearErrors()

        populateValidParams(params)
        params.id = scene.id
        params.version = -1
        controller.update()

        assert view == "/scene/edit"
        assert model.sceneInstance != null
        assert model.sceneInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/scene/list'

        response.reset()

        populateValidParams(params)
        def scene = new Scene(params)

        assert scene.save() != null
        assert Scene.count() == 1

        params.id = scene.id

        controller.delete()

        assert Scene.count() == 0
        assert Scene.get(scene.id) == null
        assert response.redirectedUrl == '/scene/list'
    }
}
