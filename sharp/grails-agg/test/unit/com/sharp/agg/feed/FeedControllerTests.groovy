package com.sharp.agg.feed



import org.junit.*
import grails.test.mixin.*

@TestFor(FeedController)
@Mock(Feed)
class FeedControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/feed/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.feedInstanceList.size() == 0
        assert model.feedInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.feedInstance != null
    }

    void testSave() {
        controller.save()

        assert model.feedInstance != null
        assert view == '/feed/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/feed/show/1'
        assert controller.flash.message != null
        assert Feed.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/feed/list'

        populateValidParams(params)
        def feed = new Feed(params)

        assert feed.save() != null

        params.id = feed.id

        def model = controller.show()

        assert model.feedInstance == feed
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/feed/list'

        populateValidParams(params)
        def feed = new Feed(params)

        assert feed.save() != null

        params.id = feed.id

        def model = controller.edit()

        assert model.feedInstance == feed
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/feed/list'

        response.reset()

        populateValidParams(params)
        def feed = new Feed(params)

        assert feed.save() != null

        // test invalid parameters in update
        params.id = feed.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/feed/edit"
        assert model.feedInstance != null

        feed.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/feed/show/$feed.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        feed.clearErrors()

        populateValidParams(params)
        params.id = feed.id
        params.version = -1
        controller.update()

        assert view == "/feed/edit"
        assert model.feedInstance != null
        assert model.feedInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/feed/list'

        response.reset()

        populateValidParams(params)
        def feed = new Feed(params)

        assert feed.save() != null
        assert Feed.count() == 1

        params.id = feed.id

        controller.delete()

        assert Feed.count() == 0
        assert Feed.findById(feed.id) == null
        assert response.redirectedUrl == '/feed/list'
    }
}
