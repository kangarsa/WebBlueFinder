package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class PIAWrapperController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [PIAWrapperInstanceList: PIAWrapper.list(params), PIAWrapperInstanceTotal: PIAWrapper.count()]
    }

    def create() {
        [PIAWrapperInstance: new PIAWrapper(params)]
    }

    def save() {
        def PIAWrapperInstance = new PIAWrapper(params)
        if (!PIAWrapperInstance.save(flush: true)) {
            render(view: "create", model: [PIAWrapperInstance: PIAWrapperInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), PIAWrapperInstance.id])
        redirect(action: "show", id: PIAWrapperInstance.id)
    }

    def show(Long id) {
        def PIAWrapperInstance = PIAWrapper.get(id)
        if (!PIAWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), id])
            redirect(action: "list")
            return
        }

        [PIAWrapperInstance: PIAWrapperInstance]
    }

    def edit(Long id) {
        def PIAWrapperInstance = PIAWrapper.get(id)
        if (!PIAWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), id])
            redirect(action: "list")
            return
        }

        [PIAWrapperInstance: PIAWrapperInstance]
    }

    def update(Long id, Long version) {
        def PIAWrapperInstance = PIAWrapper.get(id)
        if (!PIAWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (PIAWrapperInstance.version > version) {
                PIAWrapperInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'PIAWrapper.label', default: 'PIAWrapper')] as Object[],
                          "Another user has updated this PIAWrapper while you were editing")
                render(view: "edit", model: [PIAWrapperInstance: PIAWrapperInstance])
                return
            }
        }

        PIAWrapperInstance.properties = params

        if (!PIAWrapperInstance.save(flush: true)) {
            render(view: "edit", model: [PIAWrapperInstance: PIAWrapperInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), PIAWrapperInstance.id])
        redirect(action: "show", id: PIAWrapperInstance.id)
    }

    def delete(Long id) {
        def PIAWrapperInstance = PIAWrapper.get(id)
        if (!PIAWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), id])
            redirect(action: "list")
            return
        }

        try {
            PIAWrapperInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'PIAWrapper.label', default: 'PIAWrapper'), id])
            redirect(action: "show", id: id)
        }
    }
}
