package nl.wesselbarten.newsapp.data.repository

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.domain.repository.ArticleRepository
import javax.inject.Inject

@Suppress("EXPERIMENTAL_API_USAGE")
class DefaultArticleRepository @Inject constructor(
    private val articlesDataSource: ArticlesDataSource
) : ArticleRepository {

    private val newsItemsChannel = ConflatedBroadcastChannel<List<Article>>()

    override fun observe(): Flow<List<Article>> {
        return newsItemsChannel.asFlow()
    }

    override suspend fun refresh() {
        TODO("Not yet implemented")
    }
}