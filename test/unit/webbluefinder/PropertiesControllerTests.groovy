package webbluefinder



import org.junit.*
import grails.test.mixin.*

@TestFor(PropertiesController)
@Mock(Properties)
class PropertiesControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/properties/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.propertiesInstanceList.size() == 0
        assert model.propertiesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.propertiesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.propertiesInstance != null
        assert view == '/properties/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/properties/show/1'
        assert controller.flash.message != null
        assert Properties.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/properties/list'

        populateValidParams(params)
        def properties = new Properties(params)

        assert properties.save() != null

        params.id = properties.id

        def model = controller.show()

        assert model.propertiesInstance == properties
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/properties/list'

        populateValidParams(params)
        def properties = new Properties(params)

        assert properties.save() != null

        params.id = properties.id

        def model = controller.edit()

        assert model.propertiesInstance == properties
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/properties/list'

        response.reset()

        populateValidParams(params)
        def properties = new Properties(params)

        assert properties.save() != null

        // test invalid parameters in update
        params.id = properties.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/properties/edit"
        assert model.propertiesInstance != null

        properties.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/properties/show/$properties.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        properties.clearErrors()

        populateValidParams(params)
        params.id = properties.id
        params.version = -1
        controller.update()

        assert view == "/properties/edit"
        assert model.propertiesInstance != null
        assert model.propertiesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/properties/list'

        response.reset()

        populateValidParams(params)
        def properties = new Properties(params)

        assert properties.save() != null
        assert Properties.count() == 1

        params.id = properties.id

        controller.delete()

        assert Properties.count() == 0
        assert Properties.get(properties.id) == null
        assert response.redirectedUrl == '/properties/list'
    }
}
