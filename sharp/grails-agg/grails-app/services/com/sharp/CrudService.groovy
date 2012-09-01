package com.sharp

class CrudService {

    def list(classRef, params) {
        params.max = Math.min(params.max as Integer ?: 10, 100)
        [list: classRef.list(params), total: classRef.count()]
    }

    def create(classRef, params) {
        [instance: classRef.newInstance(params)]
    }

    def save(instance) {
        return instance.save(flush: true)
    }

    def update(instance, params) {
        instance.properties = params
        return save(instance)
    }

    def delete(classRef, Long id) {
        def instance = classRef.get(id)
        return instance.delete(flush: true)
    }
}
