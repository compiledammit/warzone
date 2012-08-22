package com.compiledammit



import org.junit.*
import grails.test.mixin.*

@TestFor(AdminUserController)
@Mock(AdminUser)
class AdminUserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/adminUser/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.adminUserInstanceList.size() == 0
        assert model.adminUserInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.adminUserInstance != null
    }

    void testSave() {
        controller.save()

        assert model.adminUserInstance != null
        assert view == '/adminUser/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/adminUser/show/1'
        assert controller.flash.message != null
        assert AdminUser.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/adminUser/list'

        populateValidParams(params)
        def adminUser = new AdminUser(params)

        assert adminUser.save() != null

        params.id = adminUser.id

        def model = controller.show()

        assert model.adminUserInstance == adminUser
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/adminUser/list'

        populateValidParams(params)
        def adminUser = new AdminUser(params)

        assert adminUser.save() != null

        params.id = adminUser.id

        def model = controller.edit()

        assert model.adminUserInstance == adminUser
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/adminUser/list'

        response.reset()

        populateValidParams(params)
        def adminUser = new AdminUser(params)

        assert adminUser.save() != null

        // test invalid parameters in update
        params.id = adminUser.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/adminUser/edit"
        assert model.adminUserInstance != null

        adminUser.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/adminUser/show/$adminUser.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        adminUser.clearErrors()

        populateValidParams(params)
        params.id = adminUser.id
        params.version = -1
        controller.update()

        assert view == "/adminUser/edit"
        assert model.adminUserInstance != null
        assert model.adminUserInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/adminUser/list'

        response.reset()

        populateValidParams(params)
        def adminUser = new AdminUser(params)

        assert adminUser.save() != null
        assert AdminUser.count() == 1

        params.id = adminUser.id

        controller.delete()

        assert AdminUser.count() == 0
        assert AdminUser.get(adminUser.id) == null
        assert response.redirectedUrl == '/adminUser/list'
    }
}
