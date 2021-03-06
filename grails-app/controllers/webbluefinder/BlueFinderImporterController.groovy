package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * BlueFinderImporterController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class BlueFinderImporterController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BlueFinderImporter.list(params), model:[blueFinderImporterInstanceCount: BlueFinderImporter.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BlueFinderImporter.list(params), model:[blueFinderImporterInstanceCount: BlueFinderImporter.count()]
    }

    def show(BlueFinderImporter blueFinderImporterInstance) {
        respond blueFinderImporterInstance
    }

    def create() {
        respond new BlueFinderImporter(params)
    }

    @Transactional
    def save(BlueFinderImporter blueFinderImporterInstance) {
        if (blueFinderImporterInstance == null) {
            notFound()
            return
        }

        if (blueFinderImporterInstance.hasErrors()) {
            respond blueFinderImporterInstance.errors, view:'create'
            return
        }

        blueFinderImporterInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'blueFinderImporterInstance.label', default: 'BlueFinderImporter'), blueFinderImporterInstance.id])
                redirect blueFinderImporterInstance
            }
            '*' { respond blueFinderImporterInstance, [status: CREATED] }
        }
    }

    def edit(BlueFinderImporter blueFinderImporterInstance) {
        respond blueFinderImporterInstance
    }

    @Transactional
    def update(BlueFinderImporter blueFinderImporterInstance) {
        if (blueFinderImporterInstance == null) {
            notFound()
            return
        }

        if (blueFinderImporterInstance.hasErrors()) {
            respond blueFinderImporterInstance.errors, view:'edit'
            return
        }

        blueFinderImporterInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'BlueFinderImporter.label', default: 'BlueFinderImporter'), blueFinderImporterInstance.id])
                redirect blueFinderImporterInstance
            }
            '*'{ respond blueFinderImporterInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(BlueFinderImporter blueFinderImporterInstance) {

        if (blueFinderImporterInstance == null) {
            notFound()
            return
        }

        blueFinderImporterInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'BlueFinderImporter.label', default: 'BlueFinderImporter'), blueFinderImporterInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'blueFinderImporterInstance.label', default: 'BlueFinderImporter'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	@Transactional	
	def createFor() {
		def sc = Scene.get(params.id)
		if(sc.addNewBlueFinderImporter()) {
			redirect controller:"scene", action:"show", id:params.id, method:"GET"
		}
	}
}
