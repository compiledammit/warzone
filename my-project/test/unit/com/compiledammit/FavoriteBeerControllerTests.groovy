package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(FavoriteBeerController)
@Mock(FavoriteBeer)
class FavoriteBeerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/favoriteBeer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.favoriteBeerInstanceList.size() == 0
        assert model.favoriteBeerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.favoriteBeerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.favoriteBeerInstance != null
        assert view == '/favoriteBeer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/favoriteBeer/show/1'
        assert controller.flash.message != null
        assert FavoriteBeer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteBeer/list'

        populateValidParams(params)
        def favoriteBeer = new FavoriteBeer(params)

        assert favoriteBeer.save() != null

        params.id = favoriteBeer.id

        def model = controller.show()

        assert model.favoriteBeerInstance == favoriteBeer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteBeer/list'

        populateValidParams(params)
        def favoriteBeer = new FavoriteBeer(params)

        assert favoriteBeer.save() != null

        params.id = favoriteBeer.id

        def model = controller.edit()

        assert model.favoriteBeerInstance == favoriteBeer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteBeer/list'

        response.reset()

        populateValidParams(params)
        def favoriteBeer = new FavoriteBeer(params)

        assert favoriteBeer.save() != null

        // test invalid parameters in update
        params.id = favoriteBeer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/favoriteBeer/edit"
        assert model.favoriteBeerInstance != null

        favoriteBeer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/favoriteBeer/show/$favoriteBeer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        favoriteBeer.clearErrors()

        populateValidParams(params)
        params.id = favoriteBeer.id
        params.version = -1
        controller.update()

        assert view == "/favoriteBeer/edit"
        assert model.favoriteBeerInstance != null
        assert model.favoriteBeerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/favoriteBeer/list'

        response.reset()

        populateValidParams(params)
        def favoriteBeer = new FavoriteBeer(params)

        assert favoriteBeer.save() != null
        assert FavoriteBeer.count() == 1

        params.id = favoriteBeer.id

        controller.delete()

        assert FavoriteBeer.count() == 0
        assert FavoriteBeer.get(favoriteBeer.id) == null
        assert response.redirectedUrl == '/favoriteBeer/list'
    }
}
