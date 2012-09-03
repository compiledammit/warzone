package com.compiledammit

class Favorite {
    AppUser appUser

    static constraints = {
        appUser(blank: false)
    }
}
