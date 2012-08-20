class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        log.info('This is the init() method of grails-app/conf/BootStrap.groovy.  Think of it like onApplicationStart().')
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
