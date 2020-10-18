package nl.wesselbarten.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nl.wesselbarten.newsapp.domain.model.NewsItem

interface NewsItemRepository {

    fun observe(): Flow<List<NewsItem>>

    suspend fun refresh()
}