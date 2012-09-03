package com.compiledammit

class FavoriteBrand extends Favorite {
    Brand brand

    static constraints = {
        brand(blank: false)
    }
}
