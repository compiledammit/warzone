package com.compiledammit

import org.springframework.dao.DataIntegrityViolationException

class FavoriteBrandController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [favoriteBrandInstanceList: FavoriteBrand.list(params), favoriteBrandInstanceTotal: FavoriteBrand.count()]
    }

    def create() {
        [favoriteBrandInstance: new FavoriteBrand(params)]
    }

    def save() {
        def favoriteBrandInstance = new FavoriteBrand(params)
        if (!favoriteBrandInstance.save(flush: true)) {
            render(view: "create", model: [favoriteBrandInstance: favoriteBrandInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'favoriteBrand.label', default: 'FavoriteBrand'), favoriteBrandInstance.id])
        redirect(action: "show", id: favoriteBrandInstance.id)
    }

    def show(Long id) {
        def favoriteBrandInstance = FavoriteBrand.get(id)
        if (!favoriteBrandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteBrand.label', default: 'FavoriteBrand'), id])
            redirect(action: "list")
            return
        }

        [favoriteBrandInstance: favoriteBrandInstance]
    }

    def edit(Long id) {
        def favoriteBrandInstance = FavoriteBrand.get(id)
        if (!favoriteBrandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteBrand.label', default: 'FavoriteBrand'), id])
            redirect(action: "list")
            return
        }

        [favoriteBrandInstance: favoriteBrandInstance]
    }

    def update(Long id, Long version) {
        def favoriteBrandInstance = FavoriteBrand.get(id)
        if (!favoriteBrandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteBrand.label', default: 'FavoriteBrand'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (favoriteBrandInstance.version > version) {
                favoriteBrandInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'favoriteBrand.label', default: 'FavoriteBrand')] as Object[],
                        "Another user has updated this FavoriteBrand while you were editing")
                render(view: "edit", model: [favoriteBrandInstance: favoriteBrandInstance])
                return
            }
        }

        favoriteBrandInstance.properties = params

        if (!favoriteBrandInstance.save(flush: true)) {
            render(view: "edit", model: [favoriteBrandInstance: favoriteBrandInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'favoriteBrand.label', default: 'FavoriteBrand'), favoriteBrandInstance.id])
        redirect(action: "show", id: favoriteBrandInstance.id)
    }

    def delete(Long id) {
        def favoriteBrandInstance = FavoriteBrand.get(id)
        if (!favoriteBrandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteBrand.label', default: 'FavoriteBrand'), id])
            redirect(action: "list")
            return
        }

        try {
            favoriteBrandInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'favoriteBrand.label', default: 'FavoriteBrand'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'favoriteBrand.label', default: 'FavoriteBrand'), id])
            redirect(action: "show", id: id)
        }
    }
}
