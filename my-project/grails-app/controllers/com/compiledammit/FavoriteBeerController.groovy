package com.compiledammit

import org.springframework.dao.DataIntegrityViolationException

class FavoriteBeerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [favoriteBeerInstanceList: FavoriteBeer.list(params), favoriteBeerInstanceTotal: FavoriteBeer.count()]
    }

    def create() {
        [favoriteBeerInstance: new FavoriteBeer(params)]
    }

    def save() {
        def favoriteBeerInstance = new FavoriteBeer(params)
        if (!favoriteBeerInstance.save(flush: true)) {
            render(view: "create", model: [favoriteBeerInstance: favoriteBeerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'favoriteBeer.label', default: 'FavoriteBeer'), favoriteBeerInstance.id])
        redirect(action: "show", id: favoriteBeerInstance.id)
    }

    def show(Long id) {
        def favoriteBeerInstance = FavoriteBeer.get(id)
        if (!favoriteBeerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteBeer.label', default: 'FavoriteBeer'), id])
            redirect(action: "list")
            return
        }

        [favoriteBeerInstance: favoriteBeerInstance]
    }

    def edit(Long id) {
        def favoriteBeerInstance = FavoriteBeer.get(id)
        if (!favoriteBeerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteBeer.label', default: 'FavoriteBeer'), id])
            redirect(action: "list")
            return
        }

        [favoriteBeerInstance: favoriteBeerInstance]
    }

    def update(Long id, Long version) {
        def favoriteBeerInstance = FavoriteBeer.get(id)
        if (!favoriteBeerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteBeer.label', default: 'FavoriteBeer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (favoriteBeerInstance.version > version) {
                favoriteBeerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'favoriteBeer.label', default: 'FavoriteBeer')] as Object[],
                        "Another user has updated this FavoriteBeer while you were editing")
                render(view: "edit", model: [favoriteBeerInstance: favoriteBeerInstance])
                return
            }
        }

        favoriteBeerInstance.properties = params

        if (!favoriteBeerInstance.save(flush: true)) {
            render(view: "edit", model: [favoriteBeerInstance: favoriteBeerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'favoriteBeer.label', default: 'FavoriteBeer'), favoriteBeerInstance.id])
        redirect(action: "show", id: favoriteBeerInstance.id)
    }

    def delete(Long id) {
        def favoriteBeerInstance = FavoriteBeer.get(id)
        if (!favoriteBeerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteBeer.label', default: 'FavoriteBeer'), id])
            redirect(action: "list")
            return
        }

        try {
            favoriteBeerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'favoriteBeer.label', default: 'FavoriteBeer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'favoriteBeer.label', default: 'FavoriteBeer'), id])
            redirect(action: "show", id: id)
        }
    }
}
