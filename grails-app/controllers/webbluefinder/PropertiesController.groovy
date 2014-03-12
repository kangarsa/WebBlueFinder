package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class PropertiesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [propertiesInstanceList: Properties.list(params), propertiesInstanceTotal: Properties.count()]
    }

    def create() {
        [propertiesInstance: new Properties(params)]
    }

    def save() {
        def propertiesInstance = new Properties(params)
        if (!propertiesInstance.save(flush: true)) {
            render(view: "create", model: [propertiesInstance: propertiesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'properties.label', default: 'Properties'), propertiesInstance.id])
        redirect(action: "show", id: propertiesInstance.id)
    }

    def show(Long id) {
        def propertiesInstance = Properties.get(id)
        if (!propertiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'properties.label', default: 'Properties'), id])
            redirect(action: "list")
            return
        }

        [propertiesInstance: propertiesInstance]
    }

    def edit(Long id) {
        def propertiesInstance = Properties.get(id)
        if (!propertiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'properties.label', default: 'Properties'), id])
            redirect(action: "list")
            return
        }

        [propertiesInstance: propertiesInstance]
    }

    def update(Long id, Long version) {
        def propertiesInstance = Properties.get(id)
        if (!propertiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'properties.label', default: 'Properties'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (propertiesInstance.version > version) {
                propertiesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'properties.label', default: 'Properties')] as Object[],
                          "Another user has updated this Properties while you were editing")
                render(view: "edit", model: [propertiesInstance: propertiesInstance])
                return
            }
        }

        propertiesInstance.properties = params

        if (!propertiesInstance.save(flush: true)) {
            render(view: "edit", model: [propertiesInstance: propertiesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'properties.label', default: 'Properties'), propertiesInstance.id])
        redirect(action: "show", id: propertiesInstance.id)
    }

    def delete(Long id) {
        def propertiesInstance = Properties.get(id)
        if (!propertiesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'properties.label', default: 'Properties'), id])
            redirect(action: "list")
            return
        }

        try {
            propertiesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'properties.label', default: 'Properties'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'properties.label', default: 'Properties'), id])
            redirect(action: "show", id: id)
        }
    }
}
