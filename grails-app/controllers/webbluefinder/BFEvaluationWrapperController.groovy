package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * BFEvaluationWrapperController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class BFEvaluationWrapperController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BFEvaluationWrapper.list(params), model:[BFEvaluationWrapperInstanceCount: BFEvaluationWrapper.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BFEvaluationWrapper.list(params), model:[BFEvaluationWrapperInstanceCount: BFEvaluationWrapper.count()]
    }

    def show(BFEvaluationWrapper BFEvaluationWrapperInstance) {
        respond BFEvaluationWrapperInstance
    }

    def create() {
        respond new BFEvaluationWrapper(params)
    }

    @Transactional
    def save(BFEvaluationWrapper BFEvaluationWrapperInstance) {
        if (BFEvaluationWrapperInstance == null) {
            notFound()
            return
        }

        if (BFEvaluationWrapperInstance.hasErrors()) {
            respond BFEvaluationWrapperInstance.errors, view:'create'
            return
        }

        BFEvaluationWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'BFEvaluationWrapperInstance.label', default: 'BFEvaluationWrapper'), BFEvaluationWrapperInstance.id])
                redirect BFEvaluationWrapperInstance
            }
            '*' { respond BFEvaluationWrapperInstance, [status: CREATED] }
        }
    }

    def edit(BFEvaluationWrapper BFEvaluationWrapperInstance) {
        respond BFEvaluationWrapperInstance
    }

    @Transactional
    def update(BFEvaluationWrapper BFEvaluationWrapperInstance) {
        if (BFEvaluationWrapperInstance == null) {
            notFound()
            return
        }

        if (BFEvaluationWrapperInstance.hasErrors()) {
            respond BFEvaluationWrapperInstance.errors, view:'edit'
            return
        }

        BFEvaluationWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BFEvaluationWrapper.label', default: 'BFEvaluationWrapper'), BFEvaluationWrapperInstance.id])
                redirect BFEvaluationWrapperInstance
            }
            '*'{ respond BFEvaluationWrapperInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BFEvaluationWrapper BFEvaluationWrapperInstance) {

        if (BFEvaluationWrapperInstance == null) {
            notFound()
            return
        }

        BFEvaluationWrapperInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BFEvaluationWrapper.label', default: 'BFEvaluationWrapper'), BFEvaluationWrapperInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFEvaluationWrapperInstance.label', default: 'BFEvaluationWrapper'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
