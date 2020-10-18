package nl.wesselbarten.newsapp.data.source.articles

import nl.wesselbarten.newsapp.data.network.ArticleApiService
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.domain.model.Article
import javax.inject.Inject

class RemoteArticlesDataSource @Inject constructor(
    private val articleApiService: ArticleApiService
) : ArticlesDataSource {

    override suspend fun getAll(): List<Article> {
        TODO("Not yet implemented")
    }
}