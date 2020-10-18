package nl.wesselbarten.newsapp.domain.repository

import nl.wesselbarten.newsapp.domain.model.Article

interface ArticleRepository {

    suspend fun getTopHeadlines(): List<Article>
}