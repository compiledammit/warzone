package com.sharp.agg.feed

import org.springframework.dao.DataIntegrityViolationException

class FeedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [feedInstanceList: Feed.list(params), feedInstanceTotal: Feed.count()]
    }

    def create() {
        [feedInstance: new Feed(params)]
    }

    def save() {
        def feedInstance = new Feed(params)
        if (!feedInstance.save(flush: true)) {
            render(view: "create", model: [feedInstance: feedInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'feed.label', default: 'Feed'), feedInstance.id])
        redirect(action: "show", id: feedInstance.id)
    }

    def show(Long id) {
        def feedInstance = Feed.get(id)
        if (!feedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }

        [feedInstance: feedInstance]
    }

    def edit(Long id) {
        def feedInstance = Feed.get(id)
        if (!feedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }

        [feedInstance: feedInstance]
    }

    def update(Long id, Long version) {
        def feedInstance = Feed.get(id)
        if (!feedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (feedInstance.version > version) {
                feedInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'feed.label', default: 'Feed')] as Object[],
                          "Another user has updated this Feed while you were editing")
                render(view: "edit", model: [feedInstance: feedInstance])
                return
            }
        }

        feedInstance.properties = params

        if (!feedInstance.save(flush: true)) {
            render(view: "edit", model: [feedInstance: feedInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'feed.label', default: 'Feed'), feedInstance.id])
        redirect(action: "show", id: feedInstance.id)
    }

    def delete(Long id) {
        def feedInstance = Feed.get(id)
        if (!feedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }

        try {
            feedInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "show", id: id)
        }
    }
}
