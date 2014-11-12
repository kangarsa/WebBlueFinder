package webbluefinder


import static org.springframework.http.HttpStatus.*
import webbluefinder.state.NotStarted;
import grails.transaction.Transactional

/**
 * NotStartedController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class NotStartedController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NotStarted.list(params), model:[notStartedInstanceCount: NotStarted.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NotStarted.list(params), model:[notStartedInstanceCount: NotStarted.count()]
    }

    def show(NotStarted notStartedInstance) {
        respond notStartedInstance
    }

    def create() {
        respond new NotStarted(params)
    }

    @Transactional
    def save(NotStarted notStartedInstance) {
        if (notStartedInstance == null) {
            notFound()
            return
        }

        if (notStartedInstance.hasErrors()) {
            respond notStartedInstance.errors, view:'create'
            return
        }

        notStartedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'notStartedInstance.label', default: 'NotStarted'), notStartedInstance.id])
                redirect notStartedInstance
            }
            '*' { respond notStartedInstance, [status: CREATED] }
        }
    }

    def edit(NotStarted notStartedInstance) {
        respond notStartedInstance
    }

    @Transactional
    def update(NotStarted notStartedInstance) {
        if (notStartedInstance == null) {
            notFound()
            return
        }

        if (notStartedInstance.hasErrors()) {
            respond notStartedInstance.errors, view:'edit'
            return
        }

        notStartedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'NotStarted.label', default: 'NotStarted'), notStartedInstance.id])
                redirect notStartedInstance
            }
            '*'{ respond notStartedInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(NotStarted notStartedInstance) {

        if (notStartedInstance == null) {
            notFound()
            return
        }

        notStartedInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'NotStarted.label', default: 'NotStarted'), notStartedInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'notStartedInstance.label', default: 'NotStarted'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
