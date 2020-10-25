package nl.wesselbarten.newsapp.data.source.articles

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.wesselbarten.newsapp.data.Result
import nl.wesselbarten.newsapp.data.executeApiCall
import nl.wesselbarten.newsapp.data.model.toDomain
import nl.wesselbarten.newsapp.data.network.ArticleApiService
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.domain.model.Article
import javax.inject.Inject

private const val COUNTRY_NL = "nl"

class RemoteArticlesDataSource @Inject constructor(
    private val articleApiService: ArticleApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ArticlesDataSource {

    override suspend fun getTopHeadlines(): Result<List<Article>> = withContext(ioDispatcher) {
        executeApiCall {
            val response = articleApiService.getTopHeadLines(COUNTRY_NL)
            response.articles.map { it.toDomain() }
        }
    }
}