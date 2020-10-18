package nl.wesselbarten.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nl.wesselbarten.newsapp.domain.model.Article

interface ArticleRepository {

    fun observe(): Flow<List<Article>>

    suspend fun refresh()
}