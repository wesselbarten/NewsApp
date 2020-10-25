package nl.wesselbarten.newsapp.data.source

import nl.wesselbarten.newsapp.data.Result
import nl.wesselbarten.newsapp.domain.model.Article

interface ArticlesDataSource {

    suspend fun getTopHeadlines(): Result<List<Article>>
}