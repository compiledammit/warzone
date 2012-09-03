package com.sharp.agg.feed



import com.sharp.CrudService
import grails.plugins.springsecurity.Secured

@Secured("ROLE_ADMIN")
class FeedController {
    CrudService crudService
    EntryService entryService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    /* all used for public facing list */
    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def all(){
        return list()
    }

    def list(Integer max) {
        def l = crudService.list(Feed, params)
        return [feedInstanceList: l.list, feedInstanceTotal: l.total]
    }

    def create() {
        def feed = crudService.create(Feed, params)
        return [feedInstance: feed.instance]
    }

    def save() {
        def feedInstance = new Feed(params);
        def saved = crudService.save(feedInstance)
        if (saved) {
            entryService.getEntries(feedInstance)
            flash.message = message(code: 'default.created.message', args: [message(code: 'feed.label', default: 'Feed'), feedInstance.id])
            redirect(action: "show", id: feedInstance.id)
        }
        else {
            render(view: "create", model: [feedInstance: feedInstance])
        }
    }

    def show(Long id) {
        def feedInstance = Feed.get(id)
        if (!feedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }
        return [feedInstance: feedInstance]
    }

    def edit(Long id) {
        show()
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
                        "Another user has updated this ${className} while you were editing")
            }
        }

        feedInstance.properties = params
        def saved = crudService.update(feedInstance)

        if (!feedInstance.hasErrors()) {

            entryService.getEntries(feedInstance)

            flash.message = message(code: 'default.updated.message', args: [message(code: 'feed.label', default: 'Feed'), feedInstance.id])
            redirect(action: "show", id: feedInstance.id)
        }
        else {
            render(view: "edit", model: [feedInstance: feedInstance])
            return
        }
    }

    def delete(Long id) {
        def feedInstance = Feed.get(id)
        if (!feedInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feed.label', default: 'Feed'), id])
            redirect(action: "list")
            return
        }
        def deleted = crudService.delete(Feed, id)
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
