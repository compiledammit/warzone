package com.sharp.agg.feed

import grails.plugins.springsecurity.Secured

@Secured("ROLE_ADMIN")
class EntryController {
    EntryService entryService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def go(String id) {
        def entry = Entry.get(id)
        //count it...
        redirect(url: entry.link)
    }

    def byFeed(String id) {
        def feed = Feed.get(id)
        // load entries
        def dat = true
    }

    def byCategory(String id) {
        def cat = Category.get(id)
        // load entries
        def dis = false
    }

    def list(Integer max) {
        def l = entryService.list(Entry, params)
        return [entryInstanceList: l.list, entryInstanceTotal: l.total]
    }

    def create() {
        def entry = entryService.create(Entry, params)
        return [entryInstance: entry.instance]
    }

    def save() {
        def entryInstance = new Entry(params);
        def saved = entryService.save(entryInstance)
        if (saved) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'entry.label', default: 'Entry'), entryInstance.id])
            redirect(action: "show", id: entryInstance.id)
        }
        else {
            render(view: "create", model: [entryInstance: entryInstance])
        }
    }

    def show(Long id) {
        def entryInstance = Entry.get(id)
        if (!entryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "list")
            return
        }
        return [entryInstance: entryInstance]
    }

    def edit(Long id) {
        show()
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
                        "Another user has updated this ${className} while you were editing")
            }
        }

        entryInstance.properties = params
        def saved = entryService.update(entryInstance)

        if (!entryInstance.hasErrors()) {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'entry.label', default: 'Entry'), entryInstance.id])
            redirect(action: "show", id: entryInstance.id)
        }
        else {
            render(view: "edit", model: [entryInstance: entryInstance])
            return
        }
    }

    def delete(Long id) {
        def entryInstance = Entry.get(id)
        if (!entryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "list")
            return
        }
        def deleted = entryService.delete(Entry, id)
        if (deleted) {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "list")
        }
        else {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), id])
            redirect(action: "show", id: id)
        }
    }
}
