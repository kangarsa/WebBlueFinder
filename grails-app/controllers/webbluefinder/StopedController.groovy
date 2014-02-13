package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class StopedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [stopedInstanceList: Stoped.list(params), stopedInstanceTotal: Stoped.count()]
    }

    def create() {
        [stopedInstance: new Stoped(params)]
    }

    def save() {
        def stopedInstance = new Stoped(params)
        if (!stopedInstance.save(flush: true)) {
            render(view: "create", model: [stopedInstance: stopedInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'stoped.label', default: 'Stoped'), stopedInstance.id])
        redirect(action: "show", id: stopedInstance.id)
    }

    def show(Long id) {
        def stopedInstance = Stoped.get(id)
        if (!stopedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stoped.label', default: 'Stoped'), id])
            redirect(action: "list")
            return
        }

        [stopedInstance: stopedInstance]
    }

    def edit(Long id) {
        def stopedInstance = Stoped.get(id)
        if (!stopedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stoped.label', default: 'Stoped'), id])
            redirect(action: "list")
            return
        }

        [stopedInstance: stopedInstance]
    }

    def update(Long id, Long version) {
        def stopedInstance = Stoped.get(id)
        if (!stopedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stoped.label', default: 'Stoped'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (stopedInstance.version > version) {
                stopedInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'stoped.label', default: 'Stoped')] as Object[],
                          "Another user has updated this Stoped while you were editing")
                render(view: "edit", model: [stopedInstance: stopedInstance])
                return
            }
        }

        stopedInstance.properties = params

        if (!stopedInstance.save(flush: true)) {
            render(view: "edit", model: [stopedInstance: stopedInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'stoped.label', default: 'Stoped'), stopedInstance.id])
        redirect(action: "show", id: stopedInstance.id)
    }

    def delete(Long id) {
        def stopedInstance = Stoped.get(id)
        if (!stopedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stoped.label', default: 'Stoped'), id])
            redirect(action: "list")
            return
        }

        try {
            stopedInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stoped.label', default: 'Stoped'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stoped.label', default: 'Stoped'), id])
            redirect(action: "show", id: id)
        }
    }
}
