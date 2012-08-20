package com.compiledammit

import javax.servlet.http.HttpSession

class HttpSessionService {
    SingletonExampleService singletonExampleService

    // method called upon session creation
    def sessionCreated(HttpSession session) {
        log.info("Session created: " + session.id)
        singletonExampleService.sessionInit = true
    }

    //method called upon session destruction
    def sessionDestroyed(HttpSession session) {
        log.info("Session destroyed: " + session.id)
    }
}
