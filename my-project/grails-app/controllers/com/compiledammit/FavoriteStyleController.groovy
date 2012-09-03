package com.compiledammit

import org.springframework.dao.DataIntegrityViolationException

class FavoriteStyleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [favoriteStyleInstanceList: FavoriteStyle.list(params), favoriteStyleInstanceTotal: FavoriteStyle.count()]
    }

    def create() {
        [favoriteStyleInstance: new FavoriteStyle(params)]
    }

    def save() {
        def favoriteStyleInstance = new FavoriteStyle(params)
        if (!favoriteStyleInstance.save(flush: true)) {
            render(view: "create", model: [favoriteStyleInstance: favoriteStyleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'favoriteStyle.label', default: 'FavoriteStyle'), favoriteStyleInstance.id])
        redirect(action: "show", id: favoriteStyleInstance.id)
    }

    def show(Long id) {
        def favoriteStyleInstance = FavoriteStyle.get(id)
        if (!favoriteStyleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteStyle.label', default: 'FavoriteStyle'), id])
            redirect(action: "list")
            return
        }

        [favoriteStyleInstance: favoriteStyleInstance]
    }

    def edit(Long id) {
        def favoriteStyleInstance = FavoriteStyle.get(id)
        if (!favoriteStyleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteStyle.label', default: 'FavoriteStyle'), id])
            redirect(action: "list")
            return
        }

        [favoriteStyleInstance: favoriteStyleInstance]
    }

    def update(Long id, Long version) {
        def favoriteStyleInstance = FavoriteStyle.get(id)
        if (!favoriteStyleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteStyle.label', default: 'FavoriteStyle'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (favoriteStyleInstance.version > version) {
                favoriteStyleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'favoriteStyle.label', default: 'FavoriteStyle')] as Object[],
                        "Another user has updated this FavoriteStyle while you were editing")
                render(view: "edit", model: [favoriteStyleInstance: favoriteStyleInstance])
                return
            }
        }

        favoriteStyleInstance.properties = params

        if (!favoriteStyleInstance.save(flush: true)) {
            render(view: "edit", model: [favoriteStyleInstance: favoriteStyleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'favoriteStyle.label', default: 'FavoriteStyle'), favoriteStyleInstance.id])
        redirect(action: "show", id: favoriteStyleInstance.id)
    }

    def delete(Long id) {
        def favoriteStyleInstance = FavoriteStyle.get(id)
        if (!favoriteStyleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteStyle.label', default: 'FavoriteStyle'), id])
            redirect(action: "list")
            return
        }

        try {
            favoriteStyleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'favoriteStyle.label', default: 'FavoriteStyle'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'favoriteStyle.label', default: 'FavoriteStyle'), id])
            redirect(action: "show", id: id)
        }
    }
}
