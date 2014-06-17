package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * ProcessStateController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class ProcessStateController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProcessState.list(params), model:[processStateInstanceCount: ProcessState.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProcessState.list(params), model:[processStateInstanceCount: ProcessState.count()]
    }

    def show(ProcessState processStateInstance) {
        respond processStateInstance
    }

    def create() {
        respond new ProcessState(params)
    }

    @Transactional
    def save(ProcessState processStateInstance) {
        if (processStateInstance == null) {
            notFound()
            return
        }

        if (processStateInstance.hasErrors()) {
            respond processStateInstance.errors, view:'create'
            return
        }

        processStateInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'processStateInstance.label', default: 'ProcessState'), processStateInstance.id])
                redirect processStateInstance
            }
            '*' { respond processStateInstance, [status: CREATED] }
        }
    }

    def edit(ProcessState processStateInstance) {
        respond processStateInstance
    }

    @Transactional
    def update(ProcessState processStateInstance) {
        if (processStateInstance == null) {
            notFound()
            return
        }

        if (processStateInstance.hasErrors()) {
            respond processStateInstance.errors, view:'edit'
            return
        }

        processStateInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ProcessState.label', default: 'ProcessState'), processStateInstance.id])
                redirect processStateInstance
            }
            '*'{ respond processStateInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ProcessState processStateInstance) {

        if (processStateInstance == null) {
            notFound()
            return
        }

        processStateInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ProcessState.label', default: 'ProcessState'), processStateInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'processStateInstance.label', default: 'ProcessState'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
