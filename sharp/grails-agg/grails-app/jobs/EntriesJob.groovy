import com.sharp.agg.feed.EntryService
import com.sharp.agg.feed.FeedService

class EntriesJob {
    static triggers = {
        simple name: 'getEntriesTrigger', repeatInterval: 60000
    }

    EntryService entryService
    FeedService feedService

    def execute() {
        println 'executing.  this is where getEntries() will be called'
        def now = new Date()
        println now
        def staleFeeds = feedService.findNeedingUpdate()
        println staleFeeds
        staleFeeds.each { it ->
            it.lastChecked = now
            feedService.save(it)
            println('updated ' + it.title )
        }
    }
}