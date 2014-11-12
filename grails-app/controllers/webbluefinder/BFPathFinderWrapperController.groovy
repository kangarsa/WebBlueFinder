package webbluefinder


import static org.springframework.http.HttpStatus.*
import webbluefinder.process.BFPathFinderWrapper;
import grails.transaction.Transactional

/**
 * BFPathFinderWrapperController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class BFPathFinderWrapperController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BFPathFinderWrapper.list(params), model:[BFPathFinderWrapperInstanceCount: BFPathFinderWrapper.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BFPathFinderWrapper.list(params), model:[BFPathFinderWrapperInstanceCount: BFPathFinderWrapper.count()]
    }

    def show(BFPathFinderWrapper BFPathFinderWrapperInstance) {
        respond BFPathFinderWrapperInstance
    }

    def create() {
        respond new BFPathFinderWrapper(params)
    }

    @Transactional
    def save(BFPathFinderWrapper BFPathFinderWrapperInstance) {
        if (BFPathFinderWrapperInstance == null) {
            notFound()
            return
        }

        if (BFPathFinderWrapperInstance.hasErrors()) {
            respond BFPathFinderWrapperInstance.errors, view:'create'
            return
        }

        BFPathFinderWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'BFPathFinderWrapperInstance.label', default: 'BFPathFinderWrapper'), BFPathFinderWrapperInstance.id])
                redirect BFPathFinderWrapperInstance
            }
            '*' { respond BFPathFinderWrapperInstance, [status: CREATED] }
        }
    }

    def edit(BFPathFinderWrapper BFPathFinderWrapperInstance) {
        respond BFPathFinderWrapperInstance
    }

    @Transactional
    def update(BFPathFinderWrapper BFPathFinderWrapperInstance) {
        if (BFPathFinderWrapperInstance == null) {
            notFound()
            return
        }

        if (BFPathFinderWrapperInstance.hasErrors()) {
            respond BFPathFinderWrapperInstance.errors, view:'edit'
            return
        }

        BFPathFinderWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BFPathFinderWrapper.label', default: 'BFPathFinderWrapper'), BFPathFinderWrapperInstance.id])
                redirect BFPathFinderWrapperInstance
            }
            '*'{ respond BFPathFinderWrapperInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BFPathFinderWrapper BFPathFinderWrapperInstance) {

        if (BFPathFinderWrapperInstance == null) {
            notFound()
            return
        }

        BFPathFinderWrapperInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BFPathFinderWrapper.label', default: 'BFPathFinderWrapper'), BFPathFinderWrapperInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFPathFinderWrapperInstance.label', default: 'BFPathFinderWrapper'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	@Transactional	
	def createFor() {
		def sc = Scene.get(params.id)
		if(sc.addNewBFPathFinder()) {
			redirect controller:"scene", action:"show", id:params.id, method:"GET"
		}
	}
}
