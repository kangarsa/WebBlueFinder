package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class BFRecommenderWrapperController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [BFRecommenderWrapperInstanceList: BFRecommenderWrapper.list(params), BFRecommenderWrapperInstanceTotal: BFRecommenderWrapper.count()]
    }

    def create() {
        [BFRecommenderWrapperInstance: new BFRecommenderWrapper(params)]
    }

    def save() {
        def BFRecommenderWrapperInstance = new BFRecommenderWrapper(params)
        if (!BFRecommenderWrapperInstance.save(flush: true)) {
            render(view: "create", model: [BFRecommenderWrapperInstance: BFRecommenderWrapperInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), BFRecommenderWrapperInstance.id])
        redirect(action: "show", id: BFRecommenderWrapperInstance.id)
    }

    def show(Long id) {
        def BFRecommenderWrapperInstance = BFRecommenderWrapper.get(id)
        if (!BFRecommenderWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), id])
            redirect(action: "list")
            return
        }

        [BFRecommenderWrapperInstance: BFRecommenderWrapperInstance]
    }

    def edit(Long id) {
        def BFRecommenderWrapperInstance = BFRecommenderWrapper.get(id)
        if (!BFRecommenderWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), id])
            redirect(action: "list")
            return
        }

        [BFRecommenderWrapperInstance: BFRecommenderWrapperInstance]
    }

    def update(Long id, Long version) {
        def BFRecommenderWrapperInstance = BFRecommenderWrapper.get(id)
        if (!BFRecommenderWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (BFRecommenderWrapperInstance.version > version) {
                BFRecommenderWrapperInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper')] as Object[],
                          "Another user has updated this BFRecommenderWrapper while you were editing")
                render(view: "edit", model: [BFRecommenderWrapperInstance: BFRecommenderWrapperInstance])
                return
            }
        }

        BFRecommenderWrapperInstance.properties = params

        if (!BFRecommenderWrapperInstance.save(flush: true)) {
            render(view: "edit", model: [BFRecommenderWrapperInstance: BFRecommenderWrapperInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), BFRecommenderWrapperInstance.id])
        redirect(action: "show", id: BFRecommenderWrapperInstance.id)
    }

    def delete(Long id) {
        def BFRecommenderWrapperInstance = BFRecommenderWrapper.get(id)
        if (!BFRecommenderWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), id])
            redirect(action: "list")
            return
        }

        try {
            BFRecommenderWrapperInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'BFRecommenderWrapper.label', default: 'BFRecommenderWrapper'), id])
            redirect(action: "show", id: id)
        }
    }
}
