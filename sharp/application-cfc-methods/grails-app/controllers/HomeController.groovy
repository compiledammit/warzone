class HomeController {

    def grailsApplication

    def index() {
        [
            varFromServletContext: servletContext.getAttribute('appVar'),
            varFromConfig: grailsApplication.config.compiledammit.test
        ]
    }

}
