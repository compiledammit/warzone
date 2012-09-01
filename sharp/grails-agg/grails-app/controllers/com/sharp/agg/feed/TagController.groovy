package com.sharp.agg.feed



import com.sharp.CrudService

class TagController {
    CrudService crudService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        def l = crudService.list(Tag, params)
        return [tagInstanceList: l.list, tagInstanceTotal: l.total]
    }

    def create() {
        return crudService.create(Tag, params)
    }

    def save() {
        def tagInstance = new Tag(params);
        def saved = crudService.save(tagInstance)
        if (saved) {
           flash.message = message(code: 'default.created.message', args: [message(code: 'tag.label', default: 'Tag'), tagInstance.id])
           redirect(action: "show", id: tagInstance.id)
        }
        else {
           render(view: "create", model: [tagInstance: tagInstance])
        }
    }

    def show(Long id) {
        def tagInstance = Tag.get(id)
        if (!tagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), id])
            redirect(action: "list")
            return
        }
        return [tagInstance: tagInstance]
    }

    def edit(Long id) {
        show()
    }

    def update(Long id, Long version) {

        def tagInstance = Tag.get(id)

        if (!tagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tagInstance.version > version) {
                tagInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'tag.label', default: 'Tag')] as Object[],
                        "Another user has updated this ${className} while you were editing")
            }
        }

        tagInstance.properties = params
        def saved = crudService.update(tagInstance)

        if (!tagInstance.hasErrors()) {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'tag.label', default: 'Tag'), tagInstance.id])
            redirect(action: "show", id: tagInstance.id)
        }
        else {
            render(view: "edit", model: [tagInstance: tagInstance])
            return
        }
    }

    def delete(Long id) {
        def tagInstance = Tag.get(id)
        if (!tagInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tag.label', default: 'Tag'), id])
            redirect(action: "list")
            return
        }
        def deleted = crudService.delete(Tag, id)
        if (deleted) {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tag.label', default: 'Tag'), id])
            redirect(action: "list")
        }
        else {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tag.label', default: 'Tag'), id])
            redirect(action: "show", id: id)
        }
    }
}
