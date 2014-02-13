package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class ComputingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [computingInstanceList: Computing.list(params), computingInstanceTotal: Computing.count()]
    }

    def create() {
        [computingInstance: new Computing(params)]
    }

    def save() {
        def computingInstance = new Computing(params)
        if (!computingInstance.save(flush: true)) {
            render(view: "create", model: [computingInstance: computingInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'computing.label', default: 'Computing'), computingInstance.id])
        redirect(action: "show", id: computingInstance.id)
    }

    def show(Long id) {
        def computingInstance = Computing.get(id)
        if (!computingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'computing.label', default: 'Computing'), id])
            redirect(action: "list")
            return
        }

        [computingInstance: computingInstance]
    }

    def edit(Long id) {
        def computingInstance = Computing.get(id)
        if (!computingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'computing.label', default: 'Computing'), id])
            redirect(action: "list")
            return
        }

        [computingInstance: computingInstance]
    }

    def update(Long id, Long version) {
        def computingInstance = Computing.get(id)
        if (!computingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'computing.label', default: 'Computing'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (computingInstance.version > version) {
                computingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'computing.label', default: 'Computing')] as Object[],
                          "Another user has updated this Computing while you were editing")
                render(view: "edit", model: [computingInstance: computingInstance])
                return
            }
        }

        computingInstance.properties = params

        if (!computingInstance.save(flush: true)) {
            render(view: "edit", model: [computingInstance: computingInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'computing.label', default: 'Computing'), computingInstance.id])
        redirect(action: "show", id: computingInstance.id)
    }

    def delete(Long id) {
        def computingInstance = Computing.get(id)
        if (!computingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'computing.label', default: 'Computing'), id])
            redirect(action: "list")
            return
        }

        try {
            computingInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'computing.label', default: 'Computing'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'computing.label', default: 'Computing'), id])
            redirect(action: "show", id: id)
        }
    }
}
