package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(BrandController)
@Mock(Brand)
class BrandControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/brand/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.brandInstanceList.size() == 0
        assert model.brandInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.brandInstance != null
    }

    void testSave() {
        controller.save()

        assert model.brandInstance != null
        assert view == '/brand/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/brand/show/1'
        assert controller.flash.message != null
        assert Brand.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/brand/list'

        populateValidParams(params)
        def brand = new Brand(params)

        assert brand.save() != null

        params.id = brand.id

        def model = controller.show()

        assert model.brandInstance == brand
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/brand/list'

        populateValidParams(params)
        def brand = new Brand(params)

        assert brand.save() != null

        params.id = brand.id

        def model = controller.edit()

        assert model.brandInstance == brand
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/brand/list'

        response.reset()

        populateValidParams(params)
        def brand = new Brand(params)

        assert brand.save() != null

        // test invalid parameters in update
        params.id = brand.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/brand/edit"
        assert model.brandInstance != null

        brand.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/brand/show/$brand.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        brand.clearErrors()

        populateValidParams(params)
        params.id = brand.id
        params.version = -1
        controller.update()

        assert view == "/brand/edit"
        assert model.brandInstance != null
        assert model.brandInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/brand/list'

        response.reset()

        populateValidParams(params)
        def brand = new Brand(params)

        assert brand.save() != null
        assert Brand.count() == 1

        params.id = brand.id

        controller.delete()

        assert Brand.count() == 0
        assert Brand.get(brand.id) == null
        assert response.redirectedUrl == '/brand/list'
    }
}
