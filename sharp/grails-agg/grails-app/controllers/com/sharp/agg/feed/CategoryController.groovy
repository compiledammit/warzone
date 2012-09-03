package com.sharp.agg.feed

import com.sharp.CrudService
import grails.plugins.springsecurity.Secured

@Secured("ROLE_ADMIN")
class CategoryController {
    CrudService crudService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        def l = crudService.list(Category, params)
        return [categoryInstanceList: l.list, categoryInstanceTotal: l.total]
    }

    def create() {
        def categoryInstance = crudService.create(Category, params)
        return [categoryInstance: categoryInstance.instance]
    }

    def save() {
        def categoryInstance = new Category(params);
        def saved = crudService.save(categoryInstance)
        if (saved) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'category.label', default: 'Category'), categoryInstance.id])
            redirect(action: "show", id: categoryInstance.id)
        }
        else {
            render(view: "create", model: [categoryInstance: categoryInstance])
        }
    }

    def show(Long id) {
        def categoryInstance = Category.get(id)
        if (!categoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), id])
            redirect(action: "list")
            return
        }
        return [categoryInstance: categoryInstance]
    }

    def edit(Long id) {
        show()
    }

    def update(Long id, Long version) {

        def categoryInstance = Category.get(id)

        if (!categoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (categoryInstance.version > version) {
                categoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'category.label', default: 'Category')] as Object[],
                        "Another user has updated this ${className} while you were editing")
            }
        }

        categoryInstance.properties = params
        def saved = crudService.update(categoryInstance)

        if (!categoryInstance.hasErrors()) {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'category.label', default: 'Category'), categoryInstance.id])
            redirect(action: "show", id: categoryInstance.id)
        }
        else {
            render(view: "edit", model: [categoryInstance: categoryInstance])
            return
        }
    }

    def delete(Long id) {
        def categoryInstance = Category.get(id)
        if (!categoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), id])
            redirect(action: "list")
            return
        }
        def deleted = crudService.delete(Category, id)
        if (deleted) {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'category.label', default: 'Category'), id])
            redirect(action: "list")
        }
        else {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'category.label', default: 'Category'), id])
            redirect(action: "show", id: id)
        }
    }
}
