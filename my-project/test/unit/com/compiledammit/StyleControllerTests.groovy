package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(StyleController)
@Mock(Style)
class StyleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/style/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.styleInstanceList.size() == 0
        assert model.styleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.styleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.styleInstance != null
        assert view == '/style/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/style/show/1'
        assert controller.flash.message != null
        assert Style.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/style/list'

        populateValidParams(params)
        def style = new Style(params)

        assert style.save() != null

        params.id = style.id

        def model = controller.show()

        assert model.styleInstance == style
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/style/list'

        populateValidParams(params)
        def style = new Style(params)

        assert style.save() != null

        params.id = style.id

        def model = controller.edit()

        assert model.styleInstance == style
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/style/list'

        response.reset()

        populateValidParams(params)
        def style = new Style(params)

        assert style.save() != null

        // test invalid parameters in update
        params.id = style.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/style/edit"
        assert model.styleInstance != null

        style.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/style/show/$style.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        style.clearErrors()

        populateValidParams(params)
        params.id = style.id
        params.version = -1
        controller.update()

        assert view == "/style/edit"
        assert model.styleInstance != null
        assert model.styleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/style/list'

        response.reset()

        populateValidParams(params)
        def style = new Style(params)

        assert style.save() != null
        assert Style.count() == 1

        params.id = style.id

        controller.delete()

        assert Style.count() == 0
        assert Style.get(style.id) == null
        assert response.redirectedUrl == '/style/list'
    }
}
