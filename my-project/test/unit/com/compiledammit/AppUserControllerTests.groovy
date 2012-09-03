package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(AppUserController)
@Mock(AppUser)
class AppUserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/appUser/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.appUserInstanceList.size() == 0
        assert model.appUserInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.appUserInstance != null
    }

    void testSave() {
        controller.save()

        assert model.appUserInstance != null
        assert view == '/appUser/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/appUser/show/1'
        assert controller.flash.message != null
        assert AppUser.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/appUser/list'

        populateValidParams(params)
        def appUser = new AppUser(params)

        assert appUser.save() != null

        params.id = appUser.id

        def model = controller.show()

        assert model.appUserInstance == appUser
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/appUser/list'

        populateValidParams(params)
        def appUser = new AppUser(params)

        assert appUser.save() != null

        params.id = appUser.id

        def model = controller.edit()

        assert model.appUserInstance == appUser
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/appUser/list'

        response.reset()

        populateValidParams(params)
        def appUser = new AppUser(params)

        assert appUser.save() != null

        // test invalid parameters in update
        params.id = appUser.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/appUser/edit"
        assert model.appUserInstance != null

        appUser.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/appUser/show/$appUser.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        appUser.clearErrors()

        populateValidParams(params)
        params.id = appUser.id
        params.version = -1
        controller.update()

        assert view == "/appUser/edit"
        assert model.appUserInstance != null
        assert model.appUserInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/appUser/list'

        response.reset()

        populateValidParams(params)
        def appUser = new AppUser(params)

        assert appUser.save() != null
        assert AppUser.count() == 1

        params.id = appUser.id

        controller.delete()

        assert AppUser.count() == 0
        assert AppUser.get(appUser.id) == null
        assert response.redirectedUrl == '/appUser/list'
    }
}
