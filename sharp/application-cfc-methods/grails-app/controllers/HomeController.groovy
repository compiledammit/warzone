class HomeController {

    def index() {
        [varFromServletContext: servletContext.getAttribute('appVar')]
    }
}
