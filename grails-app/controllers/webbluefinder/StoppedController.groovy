package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * StoppedController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class StoppedController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Stopped.list(params), model:[stoppedInstanceCount: Stopped.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Stopped.list(params), model:[stoppedInstanceCount: Stopped.count()]
    }

    def show(Stopped stoppedInstance) {
        respond stoppedInstance
    }

    def create() {
        respond new Stopped(params)
    }

    @Transactional
    def save(Stopped stoppedInstance) {
        if (stoppedInstance == null) {
            notFound()
            return
        }

        if (stoppedInstance.hasErrors()) {
            respond stoppedInstance.errors, view:'create'
            return
        }

        stoppedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'stoppedInstance.label', default: 'Stopped'), stoppedInstance.id])
                redirect stoppedInstance
            }
            '*' { respond stoppedInstance, [status: CREATED] }
        }
    }

    def edit(Stopped stoppedInstance) {
        respond stoppedInstance
    }

    @Transactional
    def update(Stopped stoppedInstance) {
        if (stoppedInstance == null) {
            notFound()
            return
        }

        if (stoppedInstance.hasErrors()) {
            respond stoppedInstance.errors, view:'edit'
            return
        }

        stoppedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Stopped.label', default: 'Stopped'), stoppedInstance.id])
                redirect stoppedInstance
            }
            '*'{ respond stoppedInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Stopped stoppedInstance) {

        if (stoppedInstance == null) {
            notFound()
            return
        }

        stoppedInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Stopped.label', default: 'Stopped'), stoppedInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'stoppedInstance.label', default: 'Stopped'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
