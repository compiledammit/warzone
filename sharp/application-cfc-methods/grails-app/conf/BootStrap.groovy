class BootStrap {

    def init = { servletContext ->
        log.info('This is the init() method of grails-app/conf/BootStrap.groovy.  Think of it like onApplicationStart().');
        // set an "application" variable
        servletContext.setAttribute('appVar', 'This is a variable from the servlet context!');
    }
    def destroy = {
        /*
        *  per: http://grails.org/Bootstrap+Classes
        *  It is not guaranteed that {{destroy}} will be called unless the
        *  application exits gracefully (for example by using the
        *  application server's shutdown command) so don't rely on it too much
        */
        log.info('This is the destroy() method of grails-app/conf/BootStrap.groovy.  Think of it like onApplicationEnd().');
    }
}
