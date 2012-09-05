package com.sharp.agg.feed



import org.junit.*
import grails.test.mixin.*

@TestFor(EntryController)
@Mock(Entry)
class EntryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/entry/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.entryInstanceList.size() == 0
        assert model.entryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.entryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.entryInstance != null
        assert view == '/entry/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/entry/show/1'
        assert controller.flash.message != null
        assert Entry.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/entry/list'

        populateValidParams(params)
        def entry = new Entry(params)

        assert entry.save() != null

        params.id = entry.id

        def model = controller.show()

        assert model.entryInstance == entry
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/entry/list'

        populateValidParams(params)
        def entry = new Entry(params)

        assert entry.save() != null

        params.id = entry.id

        def model = controller.edit()

        assert model.entryInstance == entry
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/entry/list'

        response.reset()

        populateValidParams(params)
        def entry = new Entry(params)

        assert entry.save() != null

        // test invalid parameters in update
        params.id = entry.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/entry/edit"
        assert model.entryInstance != null

        entry.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/entry/show/$entry.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        entry.clearErrors()

        populateValidParams(params)
        params.id = entry.id
        params.version = -1
        controller.update()

        assert view == "/entry/edit"
        assert model.entryInstance != null
        assert model.entryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/entry/list'

        response.reset()

        populateValidParams(params)
        def entry = new Entry(params)

        assert entry.save() != null
        assert Entry.count() == 1

        params.id = entry.id

        controller.delete()

        assert Entry.count() == 0
        assert Entry.findById(entry.id) == null
        assert response.redirectedUrl == '/entry/list'
    }
}
