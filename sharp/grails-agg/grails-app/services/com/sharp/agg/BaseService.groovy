package com.sharp.agg

import javax.annotation.PostConstruct

class BaseService {
    def grailsApplication
    def objectClass

    @PostConstruct
    def init() {
        objectClass = grailsApplication.getClassForName(this.getClass().getName().replace('Service', ''))
    }

    def list(params) {
        params.max = Math.min(params.max as Integer ?: 10, 100)
        [instanceList: objectClass.list(params), instanceTotal: objectClass.count()]
    }

    def create(params) {
        [instance: objectClass.newInstance(params)]
    }

    def save(instance) {
        return instance.save(flush: true)
    }

    def update(instance, params) {
        instance.properties = params
        return save(instance)
    }

    def delete(Long id) {
        def instance = objectClass.get(id)
        return instance.delete(flush: true)
    }
}
