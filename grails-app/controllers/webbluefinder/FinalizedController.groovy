package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class FinalizedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [finalizedInstanceList: Finalized.list(params), finalizedInstanceTotal: Finalized.count()]
    }

    def create() {
        [finalizedInstance: new Finalized(params)]
    }

    def save() {
        def finalizedInstance = new Finalized(params)
        if (!finalizedInstance.save(flush: true)) {
            render(view: "create", model: [finalizedInstance: finalizedInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'finalized.label', default: 'Finalized'), finalizedInstance.id])
        redirect(action: "show", id: finalizedInstance.id)
    }

    def show(Long id) {
        def finalizedInstance = Finalized.get(id)
        if (!finalizedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'finalized.label', default: 'Finalized'), id])
            redirect(action: "list")
            return
        }

        [finalizedInstance: finalizedInstance]
    }

    def edit(Long id) {
        def finalizedInstance = Finalized.get(id)
        if (!finalizedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'finalized.label', default: 'Finalized'), id])
            redirect(action: "list")
            return
        }

        [finalizedInstance: finalizedInstance]
    }

    def update(Long id, Long version) {
        def finalizedInstance = Finalized.get(id)
        if (!finalizedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'finalized.label', default: 'Finalized'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (finalizedInstance.version > version) {
                finalizedInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'finalized.label', default: 'Finalized')] as Object[],
                          "Another user has updated this Finalized while you were editing")
                render(view: "edit", model: [finalizedInstance: finalizedInstance])
                return
            }
        }

        finalizedInstance.properties = params

        if (!finalizedInstance.save(flush: true)) {
            render(view: "edit", model: [finalizedInstance: finalizedInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'finalized.label', default: 'Finalized'), finalizedInstance.id])
        redirect(action: "show", id: finalizedInstance.id)
    }

    def delete(Long id) {
        def finalizedInstance = Finalized.get(id)
        if (!finalizedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'finalized.label', default: 'Finalized'), id])
            redirect(action: "list")
            return
        }

        try {
            finalizedInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'finalized.label', default: 'Finalized'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'finalized.label', default: 'Finalized'), id])
            redirect(action: "show", id: id)
        }
    }
}
