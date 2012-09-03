package com.sharp.agg.user



import org.junit.*
import grails.test.mixin.*

@TestFor(UserRoleController)
@Mock(UserRole)
class UserRoleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userRole/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userRoleInstanceList.size() == 0
        assert model.userRoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userRoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userRoleInstance != null
        assert view == '/userRole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userRole/show/1'
        assert controller.flash.message != null
        assert UserRole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userRole/list'

        populateValidParams(params)
        def userRole = new UserRole(params)

        assert userRole.save() != null

        params.id = userRole.id

        def model = controller.show()

        assert model.userRoleInstance == userRole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userRole/list'

        populateValidParams(params)
        def userRole = new UserRole(params)

        assert userRole.save() != null

        params.id = userRole.id

        def model = controller.edit()

        assert model.userRoleInstance == userRole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userRole/list'

        response.reset()

        populateValidParams(params)
        def userRole = new UserRole(params)

        assert userRole.save() != null

        // test invalid parameters in update
        params.id = userRole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userRole/edit"
        assert model.userRoleInstance != null

        userRole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userRole/show/$userRole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userRole.clearErrors()

        populateValidParams(params)
        params.id = userRole.id
        params.version = -1
        controller.update()

        assert view == "/userRole/edit"
        assert model.userRoleInstance != null
        assert model.userRoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userRole/list'

        response.reset()

        populateValidParams(params)
        def userRole = new UserRole(params)

        assert userRole.save() != null
        assert UserRole.count() == 1

        params.id = userRole.id

        controller.delete()

        assert UserRole.count() == 0
        assert UserRole.get(userRole.id) == null
        assert response.redirectedUrl == '/userRole/list'
    }
}
