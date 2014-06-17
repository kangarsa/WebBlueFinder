package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * SceneController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class SceneController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Scene.list(params), model:[sceneInstanceCount: Scene.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Scene.list(params), model:[sceneInstanceCount: Scene.count()]
    }

    def show(Scene sceneInstance) {
        respond sceneInstance
    }

    def create() {
        respond new Scene(params)
    }

    @Transactional
    def save(Scene sceneInstance) {
        if (sceneInstance == null) {
            notFound()
            return
        }

        if (sceneInstance.hasErrors()) {
            respond sceneInstance.errors, view:'create'
            return
        }

        sceneInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sceneInstance.label', default: 'Scene'), sceneInstance.id])
                redirect sceneInstance
            }
            '*' { respond sceneInstance, [status: CREATED] }
        }
    }

    def edit(Scene sceneInstance) {
        respond sceneInstance
    }

    @Transactional
    def update(Scene sceneInstance) {
        if (sceneInstance == null) {
            notFound()
            return
        }

        if (sceneInstance.hasErrors()) {
            respond sceneInstance.errors, view:'edit'
            return
        }

        sceneInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Scene.label', default: 'Scene'), sceneInstance.id])
                redirect sceneInstance
            }
            '*'{ respond sceneInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Scene sceneInstance) {

        if (sceneInstance == null) {
            notFound()
            return
        }

        sceneInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Scene.label', default: 'Scene'), sceneInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sceneInstance.label', default: 'Scene'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
