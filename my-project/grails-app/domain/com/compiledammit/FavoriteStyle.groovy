package com.compiledammit

class FavoriteStyle extends Favorite {
    Style style

    static constraints = {
        style(blank:false)
    }
}
