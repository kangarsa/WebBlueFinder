package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class ProcessStateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [processStateInstanceList: ProcessState.list(params), processStateInstanceTotal: ProcessState.count()]
    }

    def create() {
        [processStateInstance: new ProcessState(params)]
    }

    def save() {
        def processStateInstance = new ProcessState(params)
        if (!processStateInstance.save(flush: true)) {
            render(view: "create", model: [processStateInstance: processStateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'processState.label', default: 'ProcessState'), processStateInstance.id])
        redirect(action: "show", id: processStateInstance.id)
    }

    def show(Long id) {
        def processStateInstance = ProcessState.get(id)
        if (!processStateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'processState.label', default: 'ProcessState'), id])
            redirect(action: "list")
            return
        }

        [processStateInstance: processStateInstance]
    }

    def edit(Long id) {
        def processStateInstance = ProcessState.get(id)
        if (!processStateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'processState.label', default: 'ProcessState'), id])
            redirect(action: "list")
            return
        }

        [processStateInstance: processStateInstance]
    }

    def update(Long id, Long version) {
        def processStateInstance = ProcessState.get(id)
        if (!processStateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'processState.label', default: 'ProcessState'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (processStateInstance.version > version) {
                processStateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'processState.label', default: 'ProcessState')] as Object[],
                          "Another user has updated this ProcessState while you were editing")
                render(view: "edit", model: [processStateInstance: processStateInstance])
                return
            }
        }

        processStateInstance.properties = params

        if (!processStateInstance.save(flush: true)) {
            render(view: "edit", model: [processStateInstance: processStateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'processState.label', default: 'ProcessState'), processStateInstance.id])
        redirect(action: "show", id: processStateInstance.id)
    }

    def delete(Long id) {
        def processStateInstance = ProcessState.get(id)
        if (!processStateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'processState.label', default: 'ProcessState'), id])
            redirect(action: "list")
            return
        }

        try {
            processStateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'processState.label', default: 'ProcessState'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'processState.label', default: 'ProcessState'), id])
            redirect(action: "show", id: id)
        }
    }
}
