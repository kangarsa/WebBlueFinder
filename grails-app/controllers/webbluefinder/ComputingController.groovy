package webbluefinder


import static org.springframework.http.HttpStatus.*
import webbluefinder.state.Computing;
import grails.transaction.Transactional

/**
 * ComputingController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class ComputingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Computing.list(params), model:[computingInstanceCount: Computing.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Computing.list(params), model:[computingInstanceCount: Computing.count()]
    }

    def show(Computing computingInstance) {
        respond computingInstance
    }

    def create() {
        respond new Computing(params)
    }

    @Transactional
    def save(Computing computingInstance) {
        if (computingInstance == null) {
            notFound()
            return
        }

        if (computingInstance.hasErrors()) {
            respond computingInstance.errors, view:'create'
            return
        }

        computingInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'computingInstance.label', default: 'Computing'), computingInstance.id])
                redirect computingInstance
            }
            '*' { respond computingInstance, [status: CREATED] }
        }
    }

    def edit(Computing computingInstance) {
        respond computingInstance
    }

    @Transactional
    def update(Computing computingInstance) {
        if (computingInstance == null) {
            notFound()
            return
        }

        if (computingInstance.hasErrors()) {
            respond computingInstance.errors, view:'edit'
            return
        }

        computingInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Computing.label', default: 'Computing'), computingInstance.id])
                redirect computingInstance
            }
            '*'{ respond computingInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Computing computingInstance) {

        if (computingInstance == null) {
            notFound()
            return
        }

        computingInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Computing.label', default: 'Computing'), computingInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'computingInstance.label', default: 'Computing'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
