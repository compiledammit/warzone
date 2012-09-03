package com.sharp.agg.feed

import com.sharp.CrudService

class CategoryService extends CrudService {
    def findByCategory(category) {
        def byCategory = Category.findByCategory(category)
        return byCategory ?: null
    }
}
