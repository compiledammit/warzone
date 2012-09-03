package com.compiledammit

import org.springframework.dao.DataIntegrityViolationException

class AppUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [appUserInstanceList: AppUser.list(params), appUserInstanceTotal: AppUser.count()]
    }

    def create() {
        [appUserInstance: new AppUser(params)]
    }

    def save() {
        def appUserInstance = new AppUser(params)
        if (!appUserInstance.save(flush: true)) {
            render(view: "create", model: [appUserInstance: appUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'appUser.label', default: 'AppUser'), appUserInstance.id])
        redirect(action: "show", id: appUserInstance.id)
    }

    def show(Long id) {
        def appUserInstance = AppUser.get(id)
        if (!appUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'appUser.label', default: 'AppUser'), id])
            redirect(action: "list")
            return
        }

        [appUserInstance: appUserInstance]
    }

    def edit(Long id) {
        def appUserInstance = AppUser.get(id)
        if (!appUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'appUser.label', default: 'AppUser'), id])
            redirect(action: "list")
            return
        }

        [appUserInstance: appUserInstance]
    }

    def update(Long id, Long version) {
        def appUserInstance = AppUser.get(id)
        if (!appUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'appUser.label', default: 'AppUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (appUserInstance.version > version) {
                appUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'appUser.label', default: 'AppUser')] as Object[],
                        "Another user has updated this AppUser while you were editing")
                render(view: "edit", model: [appUserInstance: appUserInstance])
                return
            }
        }

        appUserInstance.properties = params

        if (!appUserInstance.save(flush: true)) {
            render(view: "edit", model: [appUserInstance: appUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'appUser.label', default: 'AppUser'), appUserInstance.id])
        redirect(action: "show", id: appUserInstance.id)
    }

    def delete(Long id) {
        def appUserInstance = AppUser.get(id)
        if (!appUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'appUser.label', default: 'AppUser'), id])
            redirect(action: "list")
            return
        }

        try {
            appUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'appUser.label', default: 'AppUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'appUser.label', default: 'AppUser'), id])
            redirect(action: "show", id: id)
        }
    }
}
