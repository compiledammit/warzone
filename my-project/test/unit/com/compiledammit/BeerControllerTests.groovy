package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(BeerController)
@Mock(Beer)
class BeerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/beer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.beerInstanceList.size() == 0
        assert model.beerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.beerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.beerInstance != null
        assert view == '/beer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/beer/show/1'
        assert controller.flash.message != null
        assert Beer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/beer/list'

        populateValidParams(params)
        def beer = new Beer(params)

        assert beer.save() != null

        params.id = beer.id

        def model = controller.show()

        assert model.beerInstance == beer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/beer/list'

        populateValidParams(params)
        def beer = new Beer(params)

        assert beer.save() != null

        params.id = beer.id

        def model = controller.edit()

        assert model.beerInstance == beer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/beer/list'

        response.reset()

        populateValidParams(params)
        def beer = new Beer(params)

        assert beer.save() != null

        // test invalid parameters in update
        params.id = beer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/beer/edit"
        assert model.beerInstance != null

        beer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/beer/show/$beer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        beer.clearErrors()

        populateValidParams(params)
        params.id = beer.id
        params.version = -1
        controller.update()

        assert view == "/beer/edit"
        assert model.beerInstance != null
        assert model.beerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/beer/list'

        response.reset()

        populateValidParams(params)
        def beer = new Beer(params)

        assert beer.save() != null
        assert Beer.count() == 1

        params.id = beer.id

        controller.delete()

        assert Beer.count() == 0
        assert Beer.get(beer.id) == null
        assert response.redirectedUrl == '/beer/list'
    }
}
