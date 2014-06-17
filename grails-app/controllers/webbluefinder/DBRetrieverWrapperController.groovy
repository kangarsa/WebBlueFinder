package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * DBRetrieverWrapperController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class DBRetrieverWrapperController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DBRetrieverWrapper.list(params), model:[DBRetrieverWrapperInstanceCount: DBRetrieverWrapper.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DBRetrieverWrapper.list(params), model:[DBRetrieverWrapperInstanceCount: DBRetrieverWrapper.count()]
    }

    def show(DBRetrieverWrapper DBRetrieverWrapperInstance) {
        respond DBRetrieverWrapperInstance
    }

    def create() {
        respond new DBRetrieverWrapper(params)
    }
	
	@Transactional	
	def createFor() {
		def sc = Scene.get(params.id)
		sc.previousProcess.add(new DBRetrieverWrapper(sc))
		if(sc) {
			redirect controller:"scene", action:"show", id:params.id, method:"GET"
		}
	}

    @Transactional
    def save(DBRetrieverWrapper DBRetrieverWrapperInstance) {
        if (DBRetrieverWrapperInstance == null) {
            notFound()
            return
        }

        if (DBRetrieverWrapperInstance.hasErrors()) {
            respond DBRetrieverWrapperInstance.errors, view:'create'
            return
        }

        DBRetrieverWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'DBRetrieverWrapperInstance.label', default: 'DBRetrieverWrapper'), DBRetrieverWrapperInstance.id])
                redirect DBRetrieverWrapperInstance
            }
            '*' { respond DBRetrieverWrapperInstance, [status: CREATED] }
        }
    }

    def edit(DBRetrieverWrapper DBRetrieverWrapperInstance) {
        respond DBRetrieverWrapperInstance
    }

    @Transactional
    def update(DBRetrieverWrapper DBRetrieverWrapperInstance) {
        if (DBRetrieverWrapperInstance == null) {
            notFound()
            return
        }

        if (DBRetrieverWrapperInstance.hasErrors()) {
            respond DBRetrieverWrapperInstance.errors, view:'edit'
            return
        }

        DBRetrieverWrapperInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), DBRetrieverWrapperInstance.id])
                redirect DBRetrieverWrapperInstance
            }
            '*'{ respond DBRetrieverWrapperInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DBRetrieverWrapper DBRetrieverWrapperInstance) {

        if (DBRetrieverWrapperInstance == null) {
            notFound()
            return
        }

        DBRetrieverWrapperInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), DBRetrieverWrapperInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'DBRetrieverWrapperInstance.label', default: 'DBRetrieverWrapper'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
