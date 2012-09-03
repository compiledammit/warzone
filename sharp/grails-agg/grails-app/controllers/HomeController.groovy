import com.sharp.agg.feed.EntryService

class HomeController {

    EntryService entryService

    def index() {
        def recentEntries = entryService.listRecent(params)
        return [entries: recentEntries.list, entriesTotal: recentEntries.total]
    }
}
