package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * DumpLoaderController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class DumpLoaderController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DumpLoader.list(params), model:[dumpLoaderInstanceCount: DumpLoader.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DumpLoader.list(params), model:[dumpLoaderInstanceCount: DumpLoader.count()]
    }

    def show(DumpLoader dumpLoaderInstance) {
        respond dumpLoaderInstance
    }

    def create() {
        respond new DumpLoader(params)
    }

    @Transactional
    def save(DumpLoader dumpLoaderInstance) {
        if (dumpLoaderInstance == null) {
            notFound()
            return
        }

        if (dumpLoaderInstance.hasErrors()) {
            respond dumpLoaderInstance.errors, view:'create'
            return
        }

        dumpLoaderInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dumpLoaderInstance.label', default: 'DumpLoader'), dumpLoaderInstance.id])
                redirect dumpLoaderInstance
            }
            '*' { respond dumpLoaderInstance, [status: CREATED] }
        }
    }

    def edit(DumpLoader dumpLoaderInstance) {
        respond dumpLoaderInstance
    }

    @Transactional
    def update(DumpLoader dumpLoaderInstance) {
        if (dumpLoaderInstance == null) {
            notFound()
            return
        }

        if (dumpLoaderInstance.hasErrors()) {
            respond dumpLoaderInstance.errors, view:'edit'
            return
        }

        dumpLoaderInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DumpLoader.label', default: 'DumpLoader'), dumpLoaderInstance.id])
                redirect dumpLoaderInstance
            }
            '*'{ respond dumpLoaderInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DumpLoader dumpLoaderInstance) {

        if (dumpLoaderInstance == null) {
            notFound()
            return
        }

        dumpLoaderInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DumpLoader.label', default: 'DumpLoader'), dumpLoaderInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dumpLoaderInstance.label', default: 'DumpLoader'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
