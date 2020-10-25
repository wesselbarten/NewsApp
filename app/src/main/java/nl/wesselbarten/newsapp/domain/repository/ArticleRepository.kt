package nl.wesselbarten.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import nl.wesselbarten.newsapp.data.Result
import nl.wesselbarten.newsapp.domain.model.Article

interface ArticleRepository {

    suspend fun setViewedArticle(article: Article): Result<Unit>

    fun getTopHeadLines(): Flow<Result<List<Article>>>

    suspend fun refreshTopHeadLines(): Result<Unit>
}