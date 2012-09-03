package com.compiledammit.security


import grails.converters.JSON
import grails.validation.ValidationErrors
import groovy.json.JsonBuilder;

import org.codehaus.groovy.grails.web.json.JSONObject;
import org.springframework.dao.DataIntegrityViolationException

class SecUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
    def list() {
      params.max = Math.min(params.max ? params.int('max') : 10, 100)
     	render SecUser.list(params) as JSON
    }

    def save() {
      def jsonObject = JSON.parse(params.secuser)
      SecUser secuserInstance = new SecUser(jsonObject)
      if (!secuserInstance.save(flush: true)) {
        ValidationErrors validationErrors = secuserInstance.errors
        render validationErrors as JSON
      }
      render secuserInstance as JSON
    }
    
    def show() {
      def secuserInstance = SecUser.get(params.id)
      if (!secuserInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'secuser.label', default: 'SecUser'), params.id])
        render flash as JSON
      }
      render SecUserInstance as JSON
    }

    def update() {
      def jsonObject = JSON.parse(params.secuser)
      SecUser secuserReceived = new SecUser(jsonObject)

        def secuserInstance = SecUser.get(jsonObject.id)
        if (!secuserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'secuser.label', default: 'SecUser'), params.id])
            render flash as JSON
        }

        if (jsonObject.version) {
          def version = jsonObject.version.toLong()
          if (secuserInstance.version > version) {
            secuserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'secuser.label', default: 'SecUser')] as Object[],
                          "Another user has updated this SecUser while you were editing")
                ValidationErrors validationErrors = secuserInstance.errors
                render validationErrors as JSON
                return
            }
        }

        secuserInstance.properties = secuserReceived.properties

        if (!secuserInstance.save(flush: true)) {
          ValidationErrors validationErrors = secuserInstance.errors
          render validationErrors as JSON
        }
		    render secuserInstance as JSON
    }

    def delete() {
      def secuserId = params.id
      def secuserInstance = SecUser.get(params.id)
      if (!secuserInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'secuser.label', default: 'SecUser'), params.id])
        render flash as JSON
      }
      try {
            secuserInstance.delete(flush: true)
      }
      catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'secuser.label', default: 'SecUser'), params.id])
        render flash as JSON
      }
      render secuserInstance as JSON
    }
}
