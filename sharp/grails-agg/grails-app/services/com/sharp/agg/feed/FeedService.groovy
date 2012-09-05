package com.sharp.agg.feed

import com.sharp.CrudService
import org.codehaus.groovy.runtime.TimeCategory

class FeedService extends CrudService {

    /* get disregards hibernate filter */

    def get(Long id) {
        def feed
        Feed.withoutHibernateFilters {
            feed = Feed.findById(id)
        }
        return feed
    }

    def listAll(params) {
        params.max = Math.min(params.max as Integer ?: 10, 100)
        def feeds, feedCount
        Feed.withoutHibernateFilters {
            feeds = Feed.list()
            feedCount = Feed.count()
        }
        return [list: feeds, total: feedCount]
    }

    def findNeedingUpdate() {
        def hourAgo
        def now = new Date()

        use(TimeCategory) {
            hourAgo = now - 1.minutes
        }

        def stale = Feed.findAllByLastCheckedLessThan(hourAgo)
        return stale
    }

    def findById(String id) {
        def feed = Feed.findById(id)
        return feed
    }
}
