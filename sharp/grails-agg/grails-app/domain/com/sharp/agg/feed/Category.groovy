package com.sharp.agg.feed

class Category {
    String category

    static constraints = {
        category(unique: true, maxSize: 100)
    }
}
