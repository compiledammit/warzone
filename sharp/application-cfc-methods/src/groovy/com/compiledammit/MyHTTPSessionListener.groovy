package com.compiledammit

import javax.servlet.http.HttpSession
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener
import org.codehaus.groovy.grails.commons.ApplicationHolder

class MyHttpSessionListener implements HttpSessionListener {
    HttpSessionService httpSessionService

    // called by servlet container upon session creation
    void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession()
        getHttpSessionService().sessionCreated(session)
    }

    // called by servlet container upon session destruction
    void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession()
        getHttpSessionService().sessionDestroyed(session)
    }

    private synchronized HttpSessionService getHttpSessionService () {
        if (httpSessionService == null) {
            httpSessionService =
                (HttpSessionService) ApplicationHolder
                        .getApplication().getMainContext()
                        .getBean("httpSessionService")
        }
        return httpSessionService
    }
}