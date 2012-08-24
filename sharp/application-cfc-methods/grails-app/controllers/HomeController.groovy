import com.compiledammit.SessionProxy
import com.compiledammit.SingletonExampleService

class HomeController {

    SingletonExampleService singletonExampleService
    SessionProxy sessionProxy

    def index() {
        // touch the session or it won't be created.
        def currentSession = session

        singletonExampleService.pageHits++
        sessionProxy.pageHits++

        grailsApplication.controllerClasses.each {
            groovy.inspect.swingui.ObjectBrowser.inspect( it )
        }

        [pageHits: singletonExampleService.pageHits, sessionHits: sessionProxy.pageHits]
    }

    def notFound() {
        def targetPage = request.forwardURI
        redirect(action: 'index')
    }

}
