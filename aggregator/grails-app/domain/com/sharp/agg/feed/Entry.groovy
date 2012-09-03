package com.sharp.agg.feed

class Entry {
    String title
    String contents
    String link
    Date postedOn
    Date aggregatedOn
    static hasMany = [tags:Tag]

    static constraints = {
    }
}
