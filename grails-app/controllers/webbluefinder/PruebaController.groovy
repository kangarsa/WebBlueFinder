package webbluefinder


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * PruebaController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class PruebaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Prueba.list(params), model:[pruebaInstanceCount: Prueba.count()]
    }

	def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Prueba.list(params), model:[pruebaInstanceCount: Prueba.count()]
    }

    def show(Prueba pruebaInstance) {
        respond pruebaInstance
    }

    def create() {
        respond new Prueba(params)
    }

    @Transactional
    def save(Prueba pruebaInstance) {
        if (pruebaInstance == null) {
            notFound()
            return
        }

        if (pruebaInstance.hasErrors()) {
            respond pruebaInstance.errors, view:'create'
            return
        }

        pruebaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pruebaInstance.label', default: 'Prueba'), pruebaInstance.id])
                redirect pruebaInstance
            }
            '*' { respond pruebaInstance, [status: CREATED] }
        }
    }

    def edit(Prueba pruebaInstance) {
        respond pruebaInstance
    }

    @Transactional
    def update(Prueba pruebaInstance) {
        if (pruebaInstance == null) {
            notFound()
            return
        }

        if (pruebaInstance.hasErrors()) {
            respond pruebaInstance.errors, view:'edit'
            return
        }

        pruebaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Prueba.label', default: 'Prueba'), pruebaInstance.id])
                redirect pruebaInstance
            }
            '*'{ respond pruebaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Prueba pruebaInstance) {

        if (pruebaInstance == null) {
            notFound()
            return
        }

        pruebaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Prueba.label', default: 'Prueba'), pruebaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pruebaInstance.label', default: 'Prueba'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
