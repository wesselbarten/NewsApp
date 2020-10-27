package nl.wesselbarten.newsapp.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import nl.wesselbarten.newsapp.data.Result
import nl.wesselbarten.newsapp.data.onSuccess
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.domain.repository.ArticleRepository
import nl.wesselbarten.newsapp.util.isNull
import javax.inject.Inject

@Suppress("EXPERIMENTAL_API_USAGE")
class DefaultArticleRepository @Inject constructor(
    private val articlesDataSource: ArticlesDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ArticleRepository {

    private val topHeadLinesChannel = ConflatedBroadcastChannel<Result<List<Article>>>()
    private val viewedArticleTitlesChannel = ConflatedBroadcastChannel<List<String>>(emptyList())

    override suspend fun setViewedArticle(article: Article): Result<Unit> =
        withContext(defaultDispatcher) {
            val newList = viewedArticleTitlesChannel.valueOrNull
                ?.toMutableList()
                ?.also { it.add(article.title) }
                ?: listOf(article.title)

            viewedArticleTitlesChannel.send(newList)

            Result.Success(Unit)
        }

    override fun getTopHeadLines(): Flow<Result<List<Article>>> {
        val topHeadLinesFlow = flow {
            if (topHeadLinesChannel.valueOrNull.isNull()) {
                val result = articlesDataSource.getTopHeadlines()
                topHeadLinesChannel.send(result)
            }
            emitAll(topHeadLinesChannel.asFlow())
        }

        return combine(
            topHeadLinesFlow,
            viewedArticleTitlesChannel.asFlow()
        ) { articlesResult, viewedTitles ->
            articlesResult.onSuccess { articles ->
                articles.map {
                    Article(
                        it.source,
                        it.author,
                        it.title,
                        it.description,
                        it.url,
                        it.imageUrl,
                        it.publishedAt,
                        it.content,
                        viewedTitles.contains(it.title)
                    )
                }
            }
        }.flowOn(defaultDispatcher)
    }

    override suspend fun refreshTopHeadLines(): Result<Unit> = withContext(defaultDispatcher) {
        val result = articlesDataSource.getTopHeadlines()
        result.onSuccess { topHeadLinesChannel.send(result) }
    }
}