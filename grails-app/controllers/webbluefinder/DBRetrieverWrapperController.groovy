package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class DBRetrieverWrapperController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [DBRetrieverWrapperInstanceList: DBRetrieverWrapper.list(params), DBRetrieverWrapperInstanceTotal: DBRetrieverWrapper.count()]
    }

    def create() {
        [DBRetrieverWrapperInstance: new DBRetrieverWrapper(params)]
    }

    def save() {
        def DBRetrieverWrapperInstance = new DBRetrieverWrapper(params)
        if (!DBRetrieverWrapperInstance.save(flush: true)) {
            render(view: "create", model: [DBRetrieverWrapperInstance: DBRetrieverWrapperInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), DBRetrieverWrapperInstance.id])
        redirect(action: "show", id: DBRetrieverWrapperInstance.id)
    }

    def show(Long id) {
        def DBRetrieverWrapperInstance = DBRetrieverWrapper.get(id)
        if (!DBRetrieverWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), id])
            redirect(action: "list")
            return
        }

        [DBRetrieverWrapperInstance: DBRetrieverWrapperInstance]
    }

    def start(Long id) {
        def DBRetrieverWrapperInstance = DBRetrieverWrapper.get(id)
        if (!DBRetrieverWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), id])
            redirect(action: "list")
            return
        }
		DBRetrieverWrapperInstance.start()
        render(view: "show", model: [DBRetrieverWrapperInstance: DBRetrieverWrapperInstance])
    }

    def edit(Long id) {
        def DBRetrieverWrapperInstance = DBRetrieverWrapper.get(id)
        if (!DBRetrieverWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), id])
            redirect(action: "list")
            return
        }

        [DBRetrieverWrapperInstance: DBRetrieverWrapperInstance]
    }

    def update(Long id, Long version) {
        def DBRetrieverWrapperInstance = DBRetrieverWrapper.get(id)
        if (!DBRetrieverWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (DBRetrieverWrapperInstance.version > version) {
                DBRetrieverWrapperInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper')] as Object[],
                          "Another user has updated this DBRetrieverWrapper while you were editing")
                render(view: "edit", model: [DBRetrieverWrapperInstance: DBRetrieverWrapperInstance])
                return
            }
        }

        DBRetrieverWrapperInstance.properties = params

        if (!DBRetrieverWrapperInstance.save(flush: true)) {
            render(view: "edit", model: [DBRetrieverWrapperInstance: DBRetrieverWrapperInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), DBRetrieverWrapperInstance.id])
        redirect(action: "show", id: DBRetrieverWrapperInstance.id)
    }

    def delete(Long id) {
        def DBRetrieverWrapperInstance = DBRetrieverWrapper.get(id)
        if (!DBRetrieverWrapperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), id])
            redirect(action: "list")
            return
        }

        try {
            DBRetrieverWrapperInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'DBRetrieverWrapper.label', default: 'DBRetrieverWrapper'), id])
            redirect(action: "show", id: id)
        }
    }
}
