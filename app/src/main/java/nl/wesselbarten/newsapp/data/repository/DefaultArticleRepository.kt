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

    private var topHeadLinesArticlesCache: List<Article>? = null
    private val viewedArticleTitles = ArrayList<String>()

    override suspend fun getTopHeadlines(forceUpdate: Boolean): List<Article> = withContext(defaultDispatcher) {
        if (forceUpdate || topHeadLinesArticlesCache == null) {
            topHeadLinesArticlesCache = articlesDataSource.getTopHeadlines()
        }
        topHeadLinesArticlesCache!!.onEach { article ->
            article.hasViewed = viewedArticleTitles.contains(article.title)
        }
    }

    override suspend fun setViewedArticle(article: Article) {
        withContext(defaultDispatcher) {
            viewedArticleTitles.add(article.title)
        }
    }
}