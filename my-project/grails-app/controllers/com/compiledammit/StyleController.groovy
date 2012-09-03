package com.compiledammit

import org.springframework.dao.DataIntegrityViolationException

class StyleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [styleInstanceList: Style.list(params), styleInstanceTotal: Style.count()]
    }

    def create() {
        [styleInstance: new Style(params)]
    }

    def save() {
        def styleInstance = new Style(params)
        if (!styleInstance.save(flush: true)) {
            render(view: "create", model: [styleInstance: styleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'style.label', default: 'Style'), styleInstance.id])
        redirect(action: "show", id: styleInstance.id)
    }

    def show(Long id) {
        def styleInstance = Style.get(id)
        if (!styleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'style.label', default: 'Style'), id])
            redirect(action: "list")
            return
        }

        [styleInstance: styleInstance]
    }

    def edit(Long id) {
        def styleInstance = Style.get(id)
        if (!styleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'style.label', default: 'Style'), id])
            redirect(action: "list")
            return
        }

        [styleInstance: styleInstance]
    }

    def update(Long id, Long version) {
        def styleInstance = Style.get(id)
        if (!styleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'style.label', default: 'Style'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (styleInstance.version > version) {
                styleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'style.label', default: 'Style')] as Object[],
                        "Another user has updated this Style while you were editing")
                render(view: "edit", model: [styleInstance: styleInstance])
                return
            }
        }

        styleInstance.properties = params

        if (!styleInstance.save(flush: true)) {
            render(view: "edit", model: [styleInstance: styleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'style.label', default: 'Style'), styleInstance.id])
        redirect(action: "show", id: styleInstance.id)
    }

    def delete(Long id) {
        def styleInstance = Style.get(id)
        if (!styleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'style.label', default: 'Style'), id])
            redirect(action: "list")
            return
        }

        try {
            styleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'style.label', default: 'Style'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'style.label', default: 'Style'), id])
            redirect(action: "show", id: id)
        }
    }
}
