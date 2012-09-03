package com.compiledammit

class FavoriteBeer extends Favorite {
    Beer beer

    static constraints = {
        beer(blank: false)
    }
}
