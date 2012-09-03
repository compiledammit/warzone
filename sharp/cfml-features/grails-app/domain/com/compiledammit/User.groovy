package com.compiledammit

class User {

    String username
    String password
    String email

    static constraints = {
        username(blank: false)
        password(blank: false, password: true)
        email(email: true, blank: false)
    }

}
