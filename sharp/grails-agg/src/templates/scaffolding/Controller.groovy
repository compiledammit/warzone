<%=packageName ? "package ${packageName}\n\n" : ''%>

import com.sharp.CrudService

class ${className}Controller {
    CrudService crudService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        def l = crudService.list(${className}, params)
        return [${propertyName}List: l.list, ${propertyName}Total: l.total]
    }

    def create() {
        def ${propertyName} = crudService.create(${className}, params)
        return [${propertyName}: ${propertyName}.instance]
    }

    def save() {
        def ${propertyName} = new ${className}(params);
        def saved = crudService.save(${propertyName})
        if (saved) {
           flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
           redirect(action: "show", id: ${propertyName}.id)
        }
        else {
           render(view: "create", model: [${propertyName}: ${propertyName}])
        }
    }

    def show(Long id) {
        def ${propertyName} = ${className}.findById(id)
        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
            return
        }
        return [${propertyName}: ${propertyName}]
    }

    def edit(Long id) {
        show()
    }

    def update(Long id, Long version) {

        def ${propertyName} = ${className}.findById(id)

        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (${propertyName}.version > version) {
                ${propertyName}.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: '${domainClass.propertyName}.label', default: '${className}')] as Object[],
                        "Another user has updated this \${className} while you were editing")
            }
        }

        ${propertyName}.properties = params
        def saved = crudService.update(${propertyName})

        if (!${propertyName}.hasErrors()) {
            flash.message = message(code: 'default.updated.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
            redirect(action: "show", id: ${propertyName}.id)
        }
        else {
            render(view: "edit", model: [${propertyName}: ${propertyName}])
            return
        }
    }

    def delete(Long id) {
        def ${propertyName} = ${className}.findById(id)
        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), id])
            redirect(action: "list")
            return
        }
        def deleted = crudService.delete(${className}, id)
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
