<%=packageName ? "package ${packageName}\n\n" : ''%>

class ${className}Controller {
    ${className}Service ${domainClass.propertyName}Service

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        return ${domainClass.propertyName}Service.list(params)
    }

    def create() {
        return ${domainClass.propertyName}Service.create(params)
    }

    def save() {
        def instance = new ${className}(params);
        def saved = ${domainClass.propertyName}Service.save(instance)
        if (saved) {
           flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), instance.id])
           redirect(action: "show", id: instance.id)
        }
        else {
           render(view: "create", model: [instance: instance])
        }
    }

    def show(Long id) {
        def instance = ${className}.get(id)
        if (!instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
            return
        }
        return [instance: instance]
    }

    def edit(Long id) {
        show()
    }

    def update(Long id, Long version) {

        def instance = ${className}.get(id)

        if (!instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (instance.version > version) {
                instance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: '${domainClass.propertyName}.label', default: '${className}')] as Object[],
                        "Another user has updated this \${className} while you were editing")
            }
        }

        def saved = ${domainClass.propertyName}Service.update(instance, params)

        if (!instance.hasErrors()) {
            flash.message = message(code: 'default.updated.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), instance.id])
            redirect(action: "show", id: instance.id)
        }
        else {
            render(view: "edit", model: [instance: instance])
            return
        }
    }

    def delete(Long id) {
        def instance = ${className}.get(id)
        if (!instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
            return
        }
        def deleted = ${domainClass.propertyName}Service.delete(id)
        if (deleted) {
            flash.message = message(code: 'default.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
        }
        else {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "show", id: id)
        }
    }
}
