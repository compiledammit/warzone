package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(GolferController)
@Mock(Golfer)
class GolferControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/golfer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.golferInstanceList.size() == 0
        assert model.golferInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.golferInstance != null
    }

    void testSave() {
        controller.save()

        assert model.golferInstance != null
        assert view == '/golfer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/golfer/show/1'
        assert controller.flash.message != null
        assert Golfer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/golfer/list'

        populateValidParams(params)
        def golfer = new Golfer(params)

        assert golfer.save() != null

        params.id = golfer.id

        def model = controller.show()

        assert model.golferInstance == golfer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/golfer/list'

        populateValidParams(params)
        def golfer = new Golfer(params)

        assert golfer.save() != null

        params.id = golfer.id

        def model = controller.edit()

        assert model.golferInstance == golfer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/golfer/list'

        response.reset()

        populateValidParams(params)
        def golfer = new Golfer(params)

        assert golfer.save() != null

        // test invalid parameters in update
        params.id = golfer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/golfer/edit"
        assert model.golferInstance != null

        golfer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/golfer/show/$golfer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        golfer.clearErrors()

        populateValidParams(params)
        params.id = golfer.id
        params.version = -1
        controller.update()

        assert view == "/golfer/edit"
        assert model.golferInstance != null
        assert model.golferInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/golfer/list'

        response.reset()

        populateValidParams(params)
        def golfer = new Golfer(params)

        assert golfer.save() != null
        assert Golfer.count() == 1

        params.id = golfer.id

        controller.delete()

        assert Golfer.count() == 0
        assert Golfer.get(golfer.id) == null
        assert response.redirectedUrl == '/golfer/list'
    }
}
