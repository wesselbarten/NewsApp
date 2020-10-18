package nl.wesselbarten.newsapp.data.source.news_items

import nl.wesselbarten.newsapp.data.source.NewsItemsDataSource
import nl.wesselbarten.newsapp.domain.model.NewsItem

class RemoteNewsItemsDataSource : NewsItemsDataSource {

    override suspend fun getAll(): List<NewsItem> {
        TODO("Not yet implemented")
    }
}