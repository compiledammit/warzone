package com.sharp.agg.feed

import grails.plugins.springsecurity.Secured

@Secured(["ROLE_ADMIN"])
class AdminController {

    def index() {
    }
}
