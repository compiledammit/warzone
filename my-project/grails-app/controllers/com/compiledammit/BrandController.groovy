package com.compiledammit

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class BrandController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [brandInstanceList: Brand.list(params), brandInstanceTotal: Brand.count()]
    }

    def create() {
        [brandInstance: new Brand(params)]
    }

    def save() {
        def brandInstance = new Brand(params)
        if (!brandInstance.save(flush: true)) {
            render(view: "create", model: [brandInstance: brandInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'brand.label', default: 'Brand'), brandInstance.id])
        redirect(action: "show", id: brandInstance.id)
    }

    def show(Long id) {
        def brandInstance = Brand.get(id)
        if (!brandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'brand.label', default: 'Brand'), id])
            redirect(action: "list")
            return
        }

        [brandInstance: brandInstance]
    }

    def edit(Long id) {
        def brandInstance = Brand.get(id)
        if (!brandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'brand.label', default: 'Brand'), id])
            redirect(action: "list")
            return
        }

        [brandInstance: brandInstance]
    }

    def update(Long id, Long version) {
        def brandInstance = Brand.get(id)
        if (!brandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'brand.label', default: 'Brand'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (brandInstance.version > version) {
                brandInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'brand.label', default: 'Brand')] as Object[],
                        "Another user has updated this Brand while you were editing")
                render(view: "edit", model: [brandInstance: brandInstance])
                return
            }
        }

        brandInstance.properties = params

        if (!brandInstance.save(flush: true)) {
            render(view: "edit", model: [brandInstance: brandInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'brand.label', default: 'Brand'), brandInstance.id])
        redirect(action: "show", id: brandInstance.id)
    }

    def delete(Long id) {
        def brandInstance = Brand.get(id)
        if (!brandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'brand.label', default: 'Brand'), id])
            redirect(action: "list")
            return
        }

        try {
            brandInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'brand.label', default: 'Brand'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'brand.label', default: 'Brand'), id])
            redirect(action: "show", id: id)
        }
    }
}
