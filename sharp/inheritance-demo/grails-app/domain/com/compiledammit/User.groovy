package com.compiledammit

class User {

    String firstName

    static ADMIN = 'Admin User'
    static GOLFER = 'Golfer'
    static USER_TYPES = [ADMIN, GOLFER]
    static constraints = {
    }
}
