package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class NotStartedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [notStartedInstanceList: NotStarted.list(params), notStartedInstanceTotal: NotStarted.count()]
    }

    def create() {
        [notStartedInstance: new NotStarted(params)]
    }

    def save() {
        def notStartedInstance = new NotStarted(params)
        if (!notStartedInstance.save(flush: true)) {
            render(view: "create", model: [notStartedInstance: notStartedInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'notStarted.label', default: 'NotStarted'), notStartedInstance.id])
        redirect(action: "show", id: notStartedInstance.id)
    }

    def show(Long id) {
        def notStartedInstance = NotStarted.get(id)
        if (!notStartedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'notStarted.label', default: 'NotStarted'), id])
            redirect(action: "list")
            return
        }

        [notStartedInstance: notStartedInstance]
    }

    def edit(Long id) {
        def notStartedInstance = NotStarted.get(id)
        if (!notStartedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'notStarted.label', default: 'NotStarted'), id])
            redirect(action: "list")
            return
        }

        [notStartedInstance: notStartedInstance]
    }

    def update(Long id, Long version) {
        def notStartedInstance = NotStarted.get(id)
        if (!notStartedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'notStarted.label', default: 'NotStarted'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (notStartedInstance.version > version) {
                notStartedInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'notStarted.label', default: 'NotStarted')] as Object[],
                          "Another user has updated this NotStarted while you were editing")
                render(view: "edit", model: [notStartedInstance: notStartedInstance])
                return
            }
        }

        notStartedInstance.properties = params

        if (!notStartedInstance.save(flush: true)) {
            render(view: "edit", model: [notStartedInstance: notStartedInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'notStarted.label', default: 'NotStarted'), notStartedInstance.id])
        redirect(action: "show", id: notStartedInstance.id)
    }

    def delete(Long id) {
        def notStartedInstance = NotStarted.get(id)
        if (!notStartedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'notStarted.label', default: 'NotStarted'), id])
            redirect(action: "list")
            return
        }

        try {
            notStartedInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'notStarted.label', default: 'NotStarted'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'notStarted.label', default: 'NotStarted'), id])
            redirect(action: "show", id: id)
        }
    }
}
