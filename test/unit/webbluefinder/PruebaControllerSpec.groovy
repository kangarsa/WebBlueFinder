package webbluefinder



import grails.test.mixin.*
import spock.lang.*

@TestFor(PruebaController)
@Mock(Prueba)
class PruebaControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.pruebaInstanceList
            model.pruebaInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.pruebaInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def prueba = new Prueba()
            prueba.validate()
            controller.save(prueba)

        then:"The create view is rendered again with the correct model"
            model.pruebaInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            prueba = new Prueba(params)

            controller.save(prueba)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/prueba/show/1'
            controller.flash.message != null
            Prueba.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def prueba = new Prueba(params)
            controller.show(prueba)

        then:"A model is populated containing the domain instance"
            model.pruebaInstance == prueba
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def prueba = new Prueba(params)
            controller.edit(prueba)

        then:"A model is populated containing the domain instance"
            model.pruebaInstance == prueba
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/prueba/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def prueba = new Prueba()
            prueba.validate()
            controller.update(prueba)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.pruebaInstance == prueba

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            prueba = new Prueba(params).save(flush: true)
            controller.update(prueba)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/prueba/show/$prueba.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/prueba/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def prueba = new Prueba(params).save(flush: true)

        then:"It exists"
            Prueba.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(prueba)

        then:"The instance is deleted"
            Prueba.count() == 0
            response.redirectedUrl == '/prueba/index'
            flash.message != null
    }
}
