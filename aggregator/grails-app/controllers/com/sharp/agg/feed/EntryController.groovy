package com.sharp.agg.feed

import org.springframework.dao.DataIntegrityViolationException

class EntryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [entryInstanceList: Entry.list(params), entryInstanceTotal: Entry.count()]
    }

    def create() {
        [entryInstance: new Entry(params)]
    }

    def save() {
        def entryInstance = new Entry(params)
        if (!entryInstance.save(flush: true)) {
            render(view: "create", model: [entryInstance: entryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'entry.label', default: 'Entry'), entryInstance.id])
        redirect(action: "show", id: entryInstance.id)
    }

    def show(Long id) {
        def entryInstance = Entry.get(id)
        if (!entryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "list")
            return
        }

        [entryInstance: entryInstance]
    }

    def edit(Long id) {
        def entryInstance = Entry.get(id)
        if (!entryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "list")
            return
        }

        [entryInstance: entryInstance]
    }

    def update(Long id, Long version) {
        def entryInstance = Entry.get(id)
        if (!entryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (entryInstance.version > version) {
                entryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'entry.label', default: 'Entry')] as Object[],
                          "Another user has updated this Entry while you were editing")
                render(view: "edit", model: [entryInstance: entryInstance])
                return
            }
        }

        entryInstance.properties = params

        if (!entryInstance.save(flush: true)) {
            render(view: "edit", model: [entryInstance: entryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'entry.label', default: 'Entry'), entryInstance.id])
        redirect(action: "show", id: entryInstance.id)
    }

    def delete(Long id) {
        def entryInstance = Entry.get(id)
        if (!entryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "list")
            return
        }

        try {
            entryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "show", id: id)
        }
    }
}
