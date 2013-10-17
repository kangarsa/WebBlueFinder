package webbluefinder

import org.springframework.dao.DataIntegrityViolationException

class SceneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [sceneInstanceList: Scene.list(params), sceneInstanceTotal: Scene.count()]
    }

    def create() {
        [sceneInstance: new Scene(params)]
    }

    def save() {
        def sceneInstance = new Scene(params)
        if (!sceneInstance.save(flush: true)) {
            render(view: "create", model: [sceneInstance: sceneInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scene.label', default: 'Scene'), sceneInstance.id])
        redirect(action: "show", id: sceneInstance.id)
    }

    def show(Long id) {
        def sceneInstance = Scene.get(id)
        if (!sceneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scene.label', default: 'Scene'), id])
            redirect(action: "list")
            return
        }

        [sceneInstance: sceneInstance]
    }

    def edit(Long id) {
        def sceneInstance = Scene.get(id)
        if (!sceneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scene.label', default: 'Scene'), id])
            redirect(action: "list")
            return
        }

        [sceneInstance: sceneInstance]
    }

    def update(Long id, Long version) {
        def sceneInstance = Scene.get(id)
        if (!sceneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scene.label', default: 'Scene'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (sceneInstance.version > version) {
                sceneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scene.label', default: 'Scene')] as Object[],
                          "Another user has updated this Scene while you were editing")
                render(view: "edit", model: [sceneInstance: sceneInstance])
                return
            }
        }

        sceneInstance.properties = params

        if (!sceneInstance.save(flush: true)) {
            render(view: "edit", model: [sceneInstance: sceneInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scene.label', default: 'Scene'), sceneInstance.id])
        redirect(action: "show", id: sceneInstance.id)
    }

    def delete(Long id) {
        def sceneInstance = Scene.get(id)
        if (!sceneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scene.label', default: 'Scene'), id])
            redirect(action: "list")
            return
        }

        try {
            sceneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scene.label', default: 'Scene'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scene.label', default: 'Scene'), id])
            redirect(action: "show", id: id)
        }
    }
}
