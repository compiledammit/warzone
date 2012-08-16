package com.sharp.agg.feed

import com.sharp.agg.BaseService

class FeedService extends BaseService {

    def getEntries(url) {
        def xmlFeed = new XmlParser().parse(url);

        def feedList = []

        (0..<xmlFeed.channel.item.size()).each {
            def item = xmlFeed.channel.item.get(it);
            Entry entry = new Entry(title: item.title.text(), link: item.link.text(), contents: item.description.text(), postedOn: item.pubDate.text())
            feedList.add(entry)
        }

        return feedList
    }
}
