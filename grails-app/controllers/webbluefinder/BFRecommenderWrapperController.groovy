package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * BFRecommenderWrapperController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class BFRecommenderWrapperController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BFRecommenderWrapper.list(params), model:[BFRecommenderWrapperInstanceCount: BFRecommenderWrapper.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BFRecommenderWrapper.list(params), model:[BFRecommenderWrapperInstanceCount: BFRecommenderWrapper.count()]
    }

    def show(BFRecommenderWrapper BFRecommenderWrapperInstance) {
        respond BFRecommenderWrapperInstance
    }

    def create() {
        respond new BFRecommenderWrapper(params)
    }

    @Transactional
    def save(BFRecommenderWrapper BFRecommenderWrapperInstance) {
        if (BFRecommenderWrapperInstance == null) {
            notFound()
            return
        }

        if (BFRecommenderWrapperInstance.hasErrors()) {
            respond BFRecommenderWrapperInstance.errors, view:'create'
            return
        }

        BFRecommenderWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'BFRecommenderWrapperInstance.label', default: 'BFRecommenderWrapper'), BFRecommenderWrapperInstance.id])
                redirect BFRecommenderWrapperInstance
            }
            '*' { respond BFRecommenderWrapperInstance, [status: CREATED] }
        }
    }

    def edit(BFRecommenderWrapper BFRecommenderWrapperInstance) {
        respond BFRecommenderWrapperInstance
    }

    @Transactional
    def update(BFRecommenderWrapper BFRecommenderWrapperInstance) {
        if (BFRecommenderWrapperInstance == null) {
            notFound()
            return
        }

        if (BFRecommenderWrapperInstance.hasErrors()) {
            respond BFRecommenderWrapperInstance.errors, view:'edit'
            return
        }

        BFRecommenderWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), BFRecommenderWrapperInstance.id])
                redirect BFRecommenderWrapperInstance
            }
            '*'{ respond BFRecommenderWrapperInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BFRecommenderWrapper BFRecommenderWrapperInstance) {

        if (BFRecommenderWrapperInstance == null) {
            notFound()
            return
        }

        BFRecommenderWrapperInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), BFRecommenderWrapperInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFRecommenderWrapperInstance.label', default: 'BFRecommenderWrapper'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	@Transactional	
	def createFor() {
		def sc = Scene.get(params.id)
		if(sc.addNewBFRecommender()) {
			redirect controller:"scene", action:"show", id:params.id, method:"GET"
		}
	}
}
