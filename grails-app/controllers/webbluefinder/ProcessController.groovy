package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * ProcessController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class ProcessController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Process.list(params), model:[processInstanceCount: Process.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Process.list(params), model:[processInstanceCount: Process.count()]
    }

    def show(Process processInstance) {
        respond processInstance
    }

    def create() {
        respond new Process(params)
    }

    @Transactional
    def save(Process processInstance) {
        if (processInstance == null) {
            notFound()
            return
        }

        if (processInstance.hasErrors()) {
            respond processInstance.errors, view:'create'
            return
        }

        processInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'processInstance.label', default: 'Process'), processInstance.id])
                redirect processInstance
            }
            '*' { respond processInstance, [status: CREATED] }
        }
    }

    def edit(Process processInstance) {
        respond processInstance
    }

    @Transactional
    def update(Process processInstance) {
        if (processInstance == null) {
            notFound()
            return
        }

        if (processInstance.hasErrors()) {
            respond processInstance.errors, view:'edit'
            return
        }

        processInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Process.label', default: 'Process'), processInstance.id])
                redirect processInstance
            }
            '*'{ respond processInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Process processInstance) {

        if (processInstance == null) {
            notFound()
            return
        }

        processInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Process.label', default: 'Process'), processInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'processInstance.label', default: 'Process'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	@Transactional
	def execute(Long id) {
		def processInstance = Process.get(id)
		if (!processInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'process.label', default: 'Process'), id])
			redirect(action: "list")
			return
		}
		
		if (processInstance.execute()) {
			flash.message = message(code: 'process.started.successful', args: [message(code: 'process.label', default: 'Process'), processInstance.name])
		} else {
			flash.message = message(code: 'process.started.failed', args: [message(code: 'process.label', default: 'Process'), processInstance.name])
		}
		redirect(controller: "scene", action: "show", id: processInstance.scene.id)
	}
}
