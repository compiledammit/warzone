package com.sharp.agg.feed

class Entry {
    String title
    String contents
    String link
    Date postedOn
    Date dateCreated

    static belongsTo = [feed: Feed]
    static hasMany = [tags: Tag]

    static constraints = {
        title(blank: false, maxSize: 250)
        link(blank:  false, url: true, unique: true, maxSize: 500)
        contents(blank: false, maxSize: 4000)
        postedOn()
    }
}
