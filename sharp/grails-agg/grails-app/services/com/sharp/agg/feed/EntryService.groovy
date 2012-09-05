package com.sharp.agg.feed

import com.sharp.CrudService
import com.sharp.agg.util.Util
import grails.gorm.PagedResultList

class EntryService extends CrudService {

    CategoryService categoryService
    FeedService feedService
    Util util

    def listRecent(params) {
        params.sort = 'postedOn'
        params.order = 'desc'
        return list(Entry, params);
    }

    def findByFeed(feed, params){
        params.max = Math.min(params.max as Integer ?: 10, 100)
        def entries = Entry.findAllByFeed(feed, params)
        def entryCount = Entry.countByFeed(feed);
        return [entries: entries, entriesTotal: entryCount, feed: feed]
    }

    def findByCategory(category, params){
        params.max = Math.min(params.max as Integer ?: 10, 100)
        def criteria = Entry.createCriteria()
        def entries = criteria.list(params) {
            categories  {
                inList('id', [category.id])
            }
        }
        return [entries: entries, entriesTotal: entries.getTotalCount(), category: category]
    }

    def getEntries(Feed feed) {
        def xmlFeed = new XmlParser().parse(feed.url);

        xmlFeed.channel.item.each { item ->
            def cats = item.category

            def categories = []

            item.category.each { cat ->
                def thisCat = cat.text()
                def exists = categoryService.findByCategory(thisCat)

                if (!exists) {
                    Category category = new Category(category: util.stripTags(util.left(thisCat, 100)))
                    categoryService.save(category)
                    categories << category
                }
                else {
                    categories << exists
                }
            }

            feed.lastChecked = new Date();

            Entry entry = new Entry(title: util.stripTags(util.left(item.title.text(), 250)), link: util.left(item.link.text(), 1000), contents: util.stripTags(util.left(item.description.text(), 4000)), postedOn: new Date(item.pubDate.text()), feed: feed, categories: categories)

            save(entry)

            if (entry.hasErrors()) {
                println entry.errors
            }
        }
    }
}
