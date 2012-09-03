package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(FavoriteStyleController)
@Mock(FavoriteStyle)
class FavoriteStyleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/favoriteStyle/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.favoriteStyleInstanceList.size() == 0
        assert model.favoriteStyleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.favoriteStyleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.favoriteStyleInstance != null
        assert view == '/favoriteStyle/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/favoriteStyle/show/1'
        assert controller.flash.message != null
        assert FavoriteStyle.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteStyle/list'

        populateValidParams(params)
        def favoriteStyle = new FavoriteStyle(params)

        assert favoriteStyle.save() != null

        params.id = favoriteStyle.id

        def model = controller.show()

        assert model.favoriteStyleInstance == favoriteStyle
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteStyle/list'

        populateValidParams(params)
        def favoriteStyle = new FavoriteStyle(params)

        assert favoriteStyle.save() != null

        params.id = favoriteStyle.id

        def model = controller.edit()

        assert model.favoriteStyleInstance == favoriteStyle
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/favoriteStyle/list'

        response.reset()

        populateValidParams(params)
        def favoriteStyle = new FavoriteStyle(params)

        assert favoriteStyle.save() != null

        // test invalid parameters in update
        params.id = favoriteStyle.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/favoriteStyle/edit"
        assert model.favoriteStyleInstance != null

        favoriteStyle.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/favoriteStyle/show/$favoriteStyle.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        favoriteStyle.clearErrors()

        populateValidParams(params)
        params.id = favoriteStyle.id
        params.version = -1
        controller.update()

        assert view == "/favoriteStyle/edit"
        assert model.favoriteStyleInstance != null
        assert model.favoriteStyleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/favoriteStyle/list'

        response.reset()

        populateValidParams(params)
        def favoriteStyle = new FavoriteStyle(params)

        assert favoriteStyle.save() != null
        assert FavoriteStyle.count() == 1

        params.id = favoriteStyle.id

        controller.delete()

        assert FavoriteStyle.count() == 0
        assert FavoriteStyle.get(favoriteStyle.id) == null
        assert response.redirectedUrl == '/favoriteStyle/list'
    }
}
