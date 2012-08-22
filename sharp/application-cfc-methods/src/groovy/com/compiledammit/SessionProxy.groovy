package com.compiledammit

import org.springframework.web.context.request.RequestContextHolder

class SessionProxy {

    def setPageHits(hits) {
        getSession().pageHits = hits
    }

    def getPageHits() {
        return getSession().pageHits ? getSession().pageHits : 0
    }

    def getSession() {
        return RequestContextHolder.currentRequestAttributes().getRequest().getSession(true)
    }
}
