package com.compiledammit

import org.springframework.dao.DataIntegrityViolationException

class AdminUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [adminUserInstanceList: AdminUser.list(params), adminUserInstanceTotal: AdminUser.count()]
    }

    def create() {
        [adminUserInstance: new AdminUser(params)]
    }

    def save() {
        def adminUserInstance = new AdminUser(params)
        if (!adminUserInstance.save(flush: true)) {
            render(view: "create", model: [adminUserInstance: adminUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'adminUser.label', default: 'AdminUser'), adminUserInstance.id])
        redirect(action: "show", id: adminUserInstance.id)
    }

    def show(Long id) {
        def adminUserInstance = AdminUser.get(id)
        if (!adminUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminUser.label', default: 'AdminUser'), id])
            redirect(action: "list")
            return
        }

        [adminUserInstance: adminUserInstance]
    }

    def edit(Long id) {
        def adminUserInstance = AdminUser.get(id)
        if (!adminUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminUser.label', default: 'AdminUser'), id])
            redirect(action: "list")
            return
        }

        [adminUserInstance: adminUserInstance]
    }

    def update(Long id, Long version) {
        def adminUserInstance = AdminUser.get(id)
        if (!adminUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminUser.label', default: 'AdminUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (adminUserInstance.version > version) {
                adminUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'adminUser.label', default: 'AdminUser')] as Object[],
                        "Another user has updated this AdminUser while you were editing")
                render(view: "edit", model: [adminUserInstance: adminUserInstance])
                return
            }
        }

        adminUserInstance.properties = params

        if (!adminUserInstance.save(flush: true)) {
            render(view: "edit", model: [adminUserInstance: adminUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'adminUser.label', default: 'AdminUser'), adminUserInstance.id])
        redirect(action: "show", id: adminUserInstance.id)
    }

    def delete(Long id) {
        def adminUserInstance = AdminUser.get(id)
        if (!adminUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminUser.label', default: 'AdminUser'), id])
            redirect(action: "list")
            return
        }

        try {
            adminUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'adminUser.label', default: 'AdminUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'adminUser.label', default: 'AdminUser'), id])
            redirect(action: "show", id: id)
        }
    }
}
