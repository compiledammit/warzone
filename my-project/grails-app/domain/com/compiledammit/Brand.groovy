package com.compiledammit

class Brand {
    String name
    SortedSet beers
    static hasMany = [beers:Beer]
    static constraints = {
    }
}
