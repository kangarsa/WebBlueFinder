package webbluefinder


import static org.springframework.http.HttpStatus.*
import webbluefinder.state.Finalized;
import grails.transaction.Transactional

/**
 * FinalizedController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class FinalizedController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Finalized.list(params), model:[finalizedInstanceCount: Finalized.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Finalized.list(params), model:[finalizedInstanceCount: Finalized.count()]
    }

    def show(Finalized finalizedInstance) {
        respond finalizedInstance
    }

    def create() {
        respond new Finalized(params)
    }

    @Transactional
    def save(Finalized finalizedInstance) {
        if (finalizedInstance == null) {
            notFound()
            return
        }

        if (finalizedInstance.hasErrors()) {
            respond finalizedInstance.errors, view:'create'
            return
        }

        finalizedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'finalizedInstance.label', default: 'Finalized'), finalizedInstance.id])
                redirect finalizedInstance
            }
            '*' { respond finalizedInstance, [status: CREATED] }
        }
    }

    def edit(Finalized finalizedInstance) {
        respond finalizedInstance
    }

    @Transactional
    def update(Finalized finalizedInstance) {
        if (finalizedInstance == null) {
            notFound()
            return
        }

        if (finalizedInstance.hasErrors()) {
            respond finalizedInstance.errors, view:'edit'
            return
        }

        finalizedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Finalized.label', default: 'Finalized'), finalizedInstance.id])
                redirect finalizedInstance
            }
            '*'{ respond finalizedInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Finalized finalizedInstance) {

        if (finalizedInstance == null) {
            notFound()
            return
        }

        finalizedInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Finalized.label', default: 'Finalized'), finalizedInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'finalizedInstance.label', default: 'Finalized'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
