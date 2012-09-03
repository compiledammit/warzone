package com.sharp.agg.feed

import com.sharp.CrudService
import org.codehaus.groovy.runtime.TimeCategory

class FeedService extends CrudService {

    def findNeedingUpdate() {
        def hourAgo
        def now = new Date()

        use(TimeCategory) {
            hourAgo = now - 1.hours
        }

        def stale = Feed.findAllByLastCheckedLessThan( hourAgo )
        return stale;
    }
}
