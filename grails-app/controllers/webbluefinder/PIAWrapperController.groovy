package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * PIAWrapperController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class PIAWrapperController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PIAWrapper.list(params), model:[PIAWrapperInstanceCount: PIAWrapper.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PIAWrapper.list(params), model:[PIAWrapperInstanceCount: PIAWrapper.count()]
    }

    def show(PIAWrapper PIAWrapperInstance) {
        respond PIAWrapperInstance
    }

    def create() {
        respond new PIAWrapper(params)
    }

    @Transactional
    def save(PIAWrapper PIAWrapperInstance) {
        if (PIAWrapperInstance == null) {
            notFound()
            return
        }

        if (PIAWrapperInstance.hasErrors()) {
            respond PIAWrapperInstance.errors, view:'create'
            return
        }

        PIAWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'PIAWrapperInstance.label', default: 'PIAWrapper'), PIAWrapperInstance.id])
                redirect PIAWrapperInstance
            }
            '*' { respond PIAWrapperInstance, [status: CREATED] }
        }
    }

    def edit(PIAWrapper PIAWrapperInstance) {
        respond PIAWrapperInstance
    }

    @Transactional
    def update(PIAWrapper PIAWrapperInstance) {
        if (PIAWrapperInstance == null) {
            notFound()
            return
        }

        if (PIAWrapperInstance.hasErrors()) {
            respond PIAWrapperInstance.errors, view:'edit'
            return
        }

        PIAWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), PIAWrapperInstance.id])
                redirect PIAWrapperInstance
            }
            '*'{ respond PIAWrapperInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(PIAWrapper PIAWrapperInstance) {

        if (PIAWrapperInstance == null) {
            notFound()
            return
        }

        PIAWrapperInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), PIAWrapperInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'PIAWrapperInstance.label', default: 'PIAWrapper'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
