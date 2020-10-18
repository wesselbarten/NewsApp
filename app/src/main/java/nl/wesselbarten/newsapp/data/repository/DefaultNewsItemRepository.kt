package nl.wesselbarten.newsapp.data.repository

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import nl.wesselbarten.newsapp.data.source.NewsItemsDataSource
import nl.wesselbarten.newsapp.domain.model.NewsItem
import nl.wesselbarten.newsapp.domain.repository.NewsItemRepository

@Suppress("EXPERIMENTAL_API_USAGE")
class DefaultNewsItemRepository(
    private val newsItemsDataSource: NewsItemsDataSource
) : NewsItemRepository {

    private val newsItemsChannel = ConflatedBroadcastChannel<List<NewsItem>>()

    override fun observe(): Flow<List<NewsItem>> {
        return newsItemsChannel.asFlow()
    }

    override suspend fun refresh() {
        TODO("Not yet implemented")
    }
}