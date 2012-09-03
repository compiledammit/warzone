package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(FavoriteBrandController)
@Mock(FavoriteBrand)
class FavoriteBrandControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/favoriteBrand/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.favoriteBrandInstanceList.size() == 0
        assert model.favoriteBrandInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.favoriteBrandInstance != null
    }

    void testSave() {
        controller.save()

        assert model.favoriteBrandInstance != null
        assert view == '/favoriteBrand/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/favoriteBrand/show/1'
        assert controller.flash.message != null
        assert FavoriteBrand.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteBrand/list'

        populateValidParams(params)
        def favoriteBrand = new FavoriteBrand(params)

        assert favoriteBrand.save() != null

        params.id = favoriteBrand.id

        def model = controller.show()

        assert model.favoriteBrandInstance == favoriteBrand
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteBrand/list'

        populateValidParams(params)
        def favoriteBrand = new FavoriteBrand(params)

        assert favoriteBrand.save() != null

        params.id = favoriteBrand.id

        def model = controller.edit()

        assert model.favoriteBrandInstance == favoriteBrand
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteBrand/list'

        response.reset()

        populateValidParams(params)
        def favoriteBrand = new FavoriteBrand(params)

        assert favoriteBrand.save() != null

        // test invalid parameters in update
        params.id = favoriteBrand.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/favoriteBrand/edit"
        assert model.favoriteBrandInstance != null

        favoriteBrand.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/favoriteBrand/show/$favoriteBrand.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        favoriteBrand.clearErrors()

        populateValidParams(params)
        params.id = favoriteBrand.id
        params.version = -1
        controller.update()

        assert view == "/favoriteBrand/edit"
        assert model.favoriteBrandInstance != null
        assert model.favoriteBrandInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/favoriteBrand/list'

        response.reset()

        populateValidParams(params)
        def favoriteBrand = new FavoriteBrand(params)

        assert favoriteBrand.save() != null
        assert FavoriteBrand.count() == 1

        params.id = favoriteBrand.id

        controller.delete()

        assert FavoriteBrand.count() == 0
        assert FavoriteBrand.get(favoriteBrand.id) == null
        assert response.redirectedUrl == '/favoriteBrand/list'
    }
}
