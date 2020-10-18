package nl.wesselbarten.newsapp.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.domain.repository.ArticleRepository
import javax.inject.Inject

class DefaultArticleRepository @Inject constructor(
    private val articlesDataSource: ArticlesDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ArticleRepository {

    override suspend fun getTopHeadlines(): List<Article> = withContext(defaultDispatcher) {
        articlesDataSource.getTopHeadlines()
    }
}