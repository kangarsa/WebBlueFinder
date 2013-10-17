package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class AbstractProcessController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [abstractProcessInstanceList: AbstractProcess.list(params), abstractProcessInstanceTotal: AbstractProcess.count()]
    }

    def create() {
        [abstractProcessInstance: new AbstractProcess(params)]
    }

    def save() {
        def abstractProcessInstance = new AbstractProcess(params)
        if (!abstractProcessInstance.save(flush: true)) {
            render(view: "create", model: [abstractProcessInstance: abstractProcessInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'abstractProcess.label', default: 'AbstractProcess'), abstractProcessInstance.id])
        redirect(action: "show", id: abstractProcessInstance.id)
    }

    def show(Long id) {
        def abstractProcessInstance = AbstractProcess.get(id)
        if (!abstractProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'abstractProcess.label', default: 'AbstractProcess'), id])
            redirect(action: "list")
            return
        }

        [abstractProcessInstance: abstractProcessInstance]
    }

    def edit(Long id) {
        def abstractProcessInstance = AbstractProcess.get(id)
        if (!abstractProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'abstractProcess.label', default: 'AbstractProcess'), id])
            redirect(action: "list")
            return
        }

        [abstractProcessInstance: abstractProcessInstance]
    }

    def update(Long id, Long version) {
        def abstractProcessInstance = AbstractProcess.get(id)
        if (!abstractProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'abstractProcess.label', default: 'AbstractProcess'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (abstractProcessInstance.version > version) {
                abstractProcessInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'abstractProcess.label', default: 'AbstractProcess')] as Object[],
                          "Another user has updated this AbstractProcess while you were editing")
                render(view: "edit", model: [abstractProcessInstance: abstractProcessInstance])
                return
            }
        }

        abstractProcessInstance.properties = params

        if (!abstractProcessInstance.save(flush: true)) {
            render(view: "edit", model: [abstractProcessInstance: abstractProcessInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'abstractProcess.label', default: 'AbstractProcess'), abstractProcessInstance.id])
        redirect(action: "show", id: abstractProcessInstance.id)
    }

    def delete(Long id) {
        def abstractProcessInstance = AbstractProcess.get(id)
        if (!abstractProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'abstractProcess.label', default: 'AbstractProcess'), id])
            redirect(action: "list")
            return
        }

        try {
            abstractProcessInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'abstractProcess.label', default: 'AbstractProcess'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'abstractProcess.label', default: 'AbstractProcess'), id])
            redirect(action: "show", id: id)
        }
    }
}
