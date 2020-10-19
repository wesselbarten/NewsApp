package nl.wesselbarten.newsapp.domain.repository

import nl.wesselbarten.newsapp.domain.model.Article

interface ArticleRepository {

    suspend fun getTopHeadlines(forceUpdate: Boolean): List<Article>

    suspend fun setViewedArticle(article: Article)
}