package com.sharp.agg.feed

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class FeedController {
    FeedService feedService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        return feedService.list(params)
    }

    def create() {
        return feedService.create(params)
    }

    def save() {
        def instance = new Feed(params);
        def saved = feedService.save(instance)
        if (saved) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'feed.label', default: 'Feed'), instance.id])
            redirect(action: "show", id: instance.id)
        }
        else {
            render(view: "create", model: [instance: instance])
        }
    }

    def show(Long id) {
        def instance = Feed.get(id)
        if (!instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }
        return [instance: instance]
    }

    def edit(Long id) {
        show()
    }

    def update(Long id, Long version) {

        def instance = Feed.get(id)

        if (!instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (instance.version > version) {
                instance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'feed.label', default: 'Feed')] as Object[],
                        "Another user has updated this ${className} while you were editing")
            }
        }

        def saved = feedService.update(instance, params)

        if (!instance.hasErrors()) {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'feed.label', default: 'Feed'), instance.id])
            redirect(action: "show", id: instance.id)
        }
        else {
            render(view: "edit", model: [instance: instance])
            return
        }
    }

    def delete(Long id) {
        def instance = Feed.get(id)
        if (!instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }
        def deleted = feedService.delete(id)
        if (deleted) {
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
        }
        else {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "show", id: id)
        }
    }
}