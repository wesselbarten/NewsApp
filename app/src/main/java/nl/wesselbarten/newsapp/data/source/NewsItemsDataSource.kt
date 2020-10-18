package nl.wesselbarten.newsapp.data.source

import nl.wesselbarten.newsapp.domain.model.NewsItem

interface NewsItemsDataSource {

    suspend fun getAll(): List<NewsItem>
}