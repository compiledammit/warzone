package com.sharp.agg.feed

import com.sharp.agg.user.User

class Feed {
    String title
    String url
    Date addedOn
    Date lastUpdatedOn
    User createdBy

    static hasMany = [entries:Entry]

    static constraints = {
        title(blank: false, maxSize: 250, unique: true);
        url(blank: false, maxSize: 1000, unique: true, url: true)
        addedOn(blank: false)
        lastUpdatedOn(blank: false)
    }
}
