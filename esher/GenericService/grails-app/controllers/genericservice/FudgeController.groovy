package genericservice

import org.springframework.dao.DataIntegrityViolationException

class FudgeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fudgeInstanceList: Fudge.list(params), fudgeInstanceTotal: Fudge.count()]
    }

    def create() {
        [fudgeInstance: new Fudge(params)]
    }

    def save() {
        def fudgeInstance = new Fudge(params)
        if (!fudgeInstance.save(flush: true)) {
            render(view: "create", model: [fudgeInstance: fudgeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fudge.label', default: 'Fudge'), fudgeInstance.id])
        redirect(action: "show", id: fudgeInstance.id)
    }

    def show(Long id) {
        def fudgeInstance = Fudge.get(id)
        if (!fudgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fudge.label', default: 'Fudge'), id])
            redirect(action: "list")
            return
        }

        [fudgeInstance: fudgeInstance]
    }

    def edit(Long id) {
        def fudgeInstance = Fudge.get(id)
        if (!fudgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fudge.label', default: 'Fudge'), id])
            redirect(action: "list")
            return
        }

        [fudgeInstance: fudgeInstance]
    }

    def update(Long id, Long version) {
        def fudgeInstance = Fudge.get(id)
        if (!fudgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fudge.label', default: 'Fudge'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fudgeInstance.version > version) {
                fudgeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'fudge.label', default: 'Fudge')] as Object[],
                        "Another user has updated this Fudge while you were editing")
                render(view: "edit", model: [fudgeInstance: fudgeInstance])
                return
            }
        }

        fudgeInstance.properties = params

        if (!fudgeInstance.save(flush: true)) {
            render(view: "edit", model: [fudgeInstance: fudgeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fudge.label', default: 'Fudge'), fudgeInstance.id])
        redirect(action: "show", id: fudgeInstance.id)
    }

    def delete(Long id) {
        def fudgeInstance = Fudge.get(id)
        if (!fudgeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fudge.label', default: 'Fudge'), id])
            redirect(action: "list")
            return
        }

        try {
            fudgeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fudge.label', default: 'Fudge'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fudge.label', default: 'Fudge'), id])
            redirect(action: "show", id: id)
        }
    }
}
