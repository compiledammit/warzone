class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        log.info('This is the init() method of grails-app/conf/BootStrap.groovy.  Think of it like onApplicationStart().')

        // set an "application" variable
        servletContext.setAttribute('appVar', 'Hello from the servlet context!')

        log.info(grailsApplication.config.compiledammit)

        // read "global" vars from Config.groovy
        def test = grailsApplication.config.compiledammit.test
        def arr = grailsApplication.config.compiledammit.array
        def map = grailsApplication.config.compiledammit.map
        def foo = true
    }
    def destroy = {
        /*
        *  per: http://grails.org/Bootstrap+Classes
        *  It is not guaranteed that {{destroy}} will be called unless the
        *  application exits gracefully (for example by using the
        *  application server's shutdown command) so don't rely on it too much
        */
        log.info('This is the destroy() method of grails-app/conf/BootStrap.groovy.  Think of it like onApplicationEnd().')
    }
}
