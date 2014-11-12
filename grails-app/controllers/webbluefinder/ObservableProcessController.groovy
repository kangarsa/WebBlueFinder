package webbluefinder


import static org.springframework.http.HttpStatus.*
import webbluefinder.process.ObservableProcess;
import grails.transaction.Transactional

/**
 * ObservableProcessController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class ObservableProcessController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ObservableProcess.list(params), model:[observableProcessInstanceCount: ObservableProcess.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ObservableProcess.list(params), model:[observableProcessInstanceCount: ObservableProcess.count()]
    }

    def show(ObservableProcess observableProcessInstance) {
        respond observableProcessInstance
    }

    def create() {
        respond new ObservableProcess(params)
    }

    @Transactional
    def save(ObservableProcess observableProcessInstance) {
        if (observableProcessInstance == null) {
            notFound()
            return
        }

        if (observableProcessInstance.hasErrors()) {
            respond observableProcessInstance.errors, view:'create'
            return
        }

        observableProcessInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'observableProcessInstance.label', default: 'ObservableProcess'), observableProcessInstance.id])
                redirect observableProcessInstance
            }
            '*' { respond observableProcessInstance, [status: CREATED] }
        }
    }

    def edit(ObservableProcess observableProcessInstance) {
        respond observableProcessInstance
    }

    @Transactional
    def update(ObservableProcess observableProcessInstance) {
        if (observableProcessInstance == null) {
            notFound()
            return
        }

        if (observableProcessInstance.hasErrors()) {
            respond observableProcessInstance.errors, view:'edit'
            return
        }

        observableProcessInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ObservableProcess.label', default: 'ObservableProcess'), observableProcessInstance.id])
                redirect observableProcessInstance
            }
            '*'{ respond observableProcessInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ObservableProcess observableProcessInstance) {

        if (observableProcessInstance == null) {
            notFound()
            return
        }

        observableProcessInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ObservableProcess.label', default: 'ObservableProcess'), observableProcessInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'observableProcessInstance.label', default: 'ObservableProcess'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
