package genericservice



import org.junit.*
import grails.test.mixin.*

@TestFor(FudgeController)
@Mock(Fudge)
class FudgeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fudge/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fudgeInstanceList.size() == 0
        assert model.fudgeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fudgeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fudgeInstance != null
        assert view == '/fudge/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fudge/show/1'
        assert controller.flash.message != null
        assert Fudge.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fudge/list'

        populateValidParams(params)
        def fudge = new Fudge(params)

        assert fudge.save() != null

        params.id = fudge.id

        def model = controller.show()

        assert model.fudgeInstance == fudge
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fudge/list'

        populateValidParams(params)
        def fudge = new Fudge(params)

        assert fudge.save() != null

        params.id = fudge.id

        def model = controller.edit()

        assert model.fudgeInstance == fudge
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fudge/list'

        response.reset()

        populateValidParams(params)
        def fudge = new Fudge(params)

        assert fudge.save() != null

        // test invalid parameters in update
        params.id = fudge.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fudge/edit"
        assert model.fudgeInstance != null

        fudge.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fudge/show/$fudge.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fudge.clearErrors()

        populateValidParams(params)
        params.id = fudge.id
        params.version = -1
        controller.update()

        assert view == "/fudge/edit"
        assert model.fudgeInstance != null
        assert model.fudgeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fudge/list'

        response.reset()

        populateValidParams(params)
        def fudge = new Fudge(params)

        assert fudge.save() != null
        assert Fudge.count() == 1

        params.id = fudge.id

        controller.delete()

        assert Fudge.count() == 0
        assert Fudge.get(fudge.id) == null
        assert response.redirectedUrl == '/fudge/list'
    }
}
