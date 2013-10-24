package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class ObservableProcessController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [observableProcessInstanceList: ObservableProcess.list(params), observableProcessInstanceTotal: ObservableProcess.count()]
    }

    def create() {
        [observableProcessInstance: new ObservableProcess(params)]
    }

    def save() {
        def observableProcessInstance = new ObservableProcess(params)
        if (!observableProcessInstance.save(flush: true)) {
            render(view: "create", model: [observableProcessInstance: observableProcessInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'observableProcess.label', default: 'ObservableProcess'), observableProcessInstance.id])
        redirect(action: "show", id: observableProcessInstance.id)
    }

    def show(Long id) {
        def observableProcessInstance = ObservableProcess.get(id)
        if (!observableProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'observableProcess.label', default: 'ObservableProcess'), id])
            redirect(action: "list")
            return
        }

        [observableProcessInstance: observableProcessInstance]
    }

    def edit(Long id) {
        def observableProcessInstance = ObservableProcess.get(id)
        if (!observableProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'observableProcess.label', default: 'ObservableProcess'), id])
            redirect(action: "list")
            return
        }

        [observableProcessInstance: observableProcessInstance]
    }

    def update(Long id, Long version) {
        def observableProcessInstance = ObservableProcess.get(id)
        if (!observableProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'observableProcess.label', default: 'ObservableProcess'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (observableProcessInstance.version > version) {
                observableProcessInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'observableProcess.label', default: 'ObservableProcess')] as Object[],
                          "Another user has updated this ObservableProcess while you were editing")
                render(view: "edit", model: [observableProcessInstance: observableProcessInstance])
                return
            }
        }

        observableProcessInstance.properties = params

        if (!observableProcessInstance.save(flush: true)) {
            render(view: "edit", model: [observableProcessInstance: observableProcessInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'observableProcess.label', default: 'ObservableProcess'), observableProcessInstance.id])
        redirect(action: "show", id: observableProcessInstance.id)
    }

    def delete(Long id) {
        def observableProcessInstance = ObservableProcess.get(id)
        if (!observableProcessInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'observableProcess.label', default: 'ObservableProcess'), id])
            redirect(action: "list")
            return
        }

        try {
            observableProcessInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'observableProcess.label', default: 'ObservableProcess'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'observableProcess.label', default: 'ObservableProcess'), id])
            redirect(action: "show", id: id)
        }
    }
}
