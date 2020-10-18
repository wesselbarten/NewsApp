package nl.wesselbarten.newsapp.data.source

import nl.wesselbarten.newsapp.domain.model.Article

interface ArticlesDataSource {

    suspend fun getTopHeadlines(): List<Article>
}