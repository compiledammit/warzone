import com.compiledammit.SingletonExampleService

class HomeController {

    SingletonExampleService singletonExampleService

    def index() {
        singletonExampleService.pageHits++
        [pageHits: singletonExampleService.pageHits, sessionInit: singletonExampleService.sessionInit]
    }

}
