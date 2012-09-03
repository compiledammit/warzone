package com.compiledammit

class Beer implements Comparable {
    String name
    BigDecimal abv
    static belongsTo = [brand: Brand]
    static hasOne = [style: Style]

    int compareTo(obj) {
        name.compareTo(obj.name)
    }

    static constraints = {
        name(blank: false, size: 1..150)
        abv(blank: false, max: 25.0)
    }
}
