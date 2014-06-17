package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * StopedController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class StopedController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Stoped.list(params), model:[stopedInstanceCount: Stoped.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Stoped.list(params), model:[stopedInstanceCount: Stoped.count()]
    }

    def show(Stoped stopedInstance) {
        respond stopedInstance
    }

    def create() {
        respond new Stoped(params)
    }

    @Transactional
    def save(Stoped stopedInstance) {
        if (stopedInstance == null) {
            notFound()
            return
        }

        if (stopedInstance.hasErrors()) {
            respond stopedInstance.errors, view:'create'
            return
        }

        stopedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'stopedInstance.label', default: 'Stoped'), stopedInstance.id])
                redirect stopedInstance
            }
            '*' { respond stopedInstance, [status: CREATED] }
        }
    }

    def edit(Stoped stopedInstance) {
        respond stopedInstance
    }

    @Transactional
    def update(Stoped stopedInstance) {
        if (stopedInstance == null) {
            notFound()
            return
        }

        if (stopedInstance.hasErrors()) {
            respond stopedInstance.errors, view:'edit'
            return
        }

        stopedInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Stoped.label', default: 'Stoped'), stopedInstance.id])
                redirect stopedInstance
            }
            '*'{ respond stopedInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Stoped stopedInstance) {

        if (stopedInstance == null) {
            notFound()
            return
        }

        stopedInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Stoped.label', default: 'Stoped'), stopedInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'stopedInstance.label', default: 'Stoped'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
