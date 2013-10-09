package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class BFWrapperController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [BFWrapperInstanceList: BFWrapper.list(params), BFWrapperInstanceTotal: BFWrapper.count()]
    }

    def create() {
        [BFWrapperInstance: new BFWrapper(params)]
    }

    def save() {
        def BFWrapperInstance = new BFWrapper(params)
        if (!BFWrapperInstance.save(flush: true)) {
            render(view: "create", model: [BFWrapperInstance: BFWrapperInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'BFWrapper.label', default: 'BFWrapper'), BFWrapperInstance.id])
        redirect(action: "show", id: BFWrapperInstance.id)
    }

    def show(Long id) {
        def BFWrapperInstance = BFWrapper.get(id)
        if (!BFWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFWrapper.label', default: 'BFWrapper'), id])
            redirect(action: "list")
            return
        }

        [BFWrapperInstance: BFWrapperInstance]
    }

    def edit(Long id) {
        def BFWrapperInstance = BFWrapper.get(id)
        if (!BFWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFWrapper.label', default: 'BFWrapper'), id])
            redirect(action: "list")
            return
        }

        [BFWrapperInstance: BFWrapperInstance]
    }

    def update(Long id, Long version) {
        def BFWrapperInstance = BFWrapper.get(id)
        if (!BFWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFWrapper.label', default: 'BFWrapper'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (BFWrapperInstance.version > version) {
                BFWrapperInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'BFWrapper.label', default: 'BFWrapper')] as Object[],
                          "Another user has updated this BFWrapper while you were editing")
                render(view: "edit", model: [BFWrapperInstance: BFWrapperInstance])
                return
            }
        }

        BFWrapperInstance.properties = params

        if (!BFWrapperInstance.save(flush: true)) {
            render(view: "edit", model: [BFWrapperInstance: BFWrapperInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'BFWrapper.label', default: 'BFWrapper'), BFWrapperInstance.id])
        redirect(action: "show", id: BFWrapperInstance.id)
    }

    def delete(Long id) {
        def BFWrapperInstance = BFWrapper.get(id)
        if (!BFWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFWrapper.label', default: 'BFWrapper'), id])
            redirect(action: "list")
            return
        }

        try {
            BFWrapperInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'BFWrapper.label', default: 'BFWrapper'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'BFWrapper.label', default: 'BFWrapper'), id])
            redirect(action: "show", id: id)
        }
    }
}
