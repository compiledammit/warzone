package com.compiledammit

import javax.servlet.http.HttpSession

class HttpSessionService {
    // method called upon session creation
    def sessionCreated(HttpSession session) {
        log.info("Session created: " + session.id)
    }

    //method called upon session destruction
    def sessionDestroyed(HttpSession session) {
        log.info("Session destroyed: " + session.id)
    }
}
