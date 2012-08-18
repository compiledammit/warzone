package extjstodo

import grails.test.mixin.*

@TestFor(TodoController)
@Mock(Todo)
class TodoControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/todo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.todoInstanceList.size() == 0
        assert model.todoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.todoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.todoInstance != null
        assert view == '/todo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/todo/show/1'
        assert controller.flash.message != null
        assert Todo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/todo/list'


        populateValidParams(params)
        def todo = new Todo(params)

        assert todo.save() != null

        params.id = todo.id

        def model = controller.show()

        assert model.todoInstance == todo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/todo/list'


        populateValidParams(params)
        def todo = new Todo(params)

        assert todo.save() != null

        params.id = todo.id

        def model = controller.edit()

        assert model.todoInstance == todo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/todo/list'

        response.reset()


        populateValidParams(params)
        def todo = new Todo(params)

        assert todo.save() != null

        // test invalid parameters in update
        params.id = todo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/todo/edit"
        assert model.todoInstance != null

        todo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/todo/show/$todo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        todo.clearErrors()

        populateValidParams(params)
        params.id = todo.id
        params.version = -1
        controller.update()

        assert view == "/todo/edit"
        assert model.todoInstance != null
        assert model.todoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/todo/list'

        response.reset()

        populateValidParams(params)
        def todo = new Todo(params)

        assert todo.save() != null
        assert Todo.count() == 1

        params.id = todo.id

        controller.delete()

        assert Todo.count() == 0
        assert Todo.get(todo.id) == null
        assert response.redirectedUrl == '/todo/list'
    }
}
