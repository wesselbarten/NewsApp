package nl.wesselbarten.newsapp.data.repository

import com.appmattus.kotlinfixture.kotlinFixture
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import nl.wesselbarten.newsapp.data.Result
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.suspendAndAwait
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.coroutines.resume

@Suppress("EXPERIMENTAL_API_USAGE")
class DefaultArticleRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val fixture = kotlinFixture()

    @Test
    fun `test get top headlines is successful`() = runBlockingTest {
        val articles = listOf(fixture<Article>())

        val mockArticlesDataSource = mock<ArticlesDataSource> {
            whenever(mock.getTopHeadlines())
                .thenReturn(Result.Success(articles))
        }

        val articleRepository = DefaultArticleRepository(
            mockArticlesDataSource,
            testDispatcher
        )

        suspendAndAwait<Unit> { continuation ->
                articleRepository.getTopHeadLines().test(this) {
                    assertNoErrors()
                    assertNotComplete()
                    assertValue { it is Result.Success }
                    assertValue { (it as Result.Success).data.size == articles.size }
                    assertValue { (it as Result.Success).data.first().title == articles.first().title }
                    continuation.resume(Unit)
                }
        }

        verify(mockArticlesDataSource, times(1)).getTopHeadlines()
    }

    @Test
    fun `test get top headlines returns error result`() = runBlockingTest {
        val mockArticlesDataSource = mock<ArticlesDataSource> {
            whenever(mock.getTopHeadlines())
                .thenReturn(Result.Error(Exception()))
        }

        val articleRepository = DefaultArticleRepository(
            mockArticlesDataSource,
            testDispatcher
        )

        suspendAndAwait<Unit> { continuation ->
            articleRepository.getTopHeadLines().test(this) {
                assertValue { it is Result.Error }
                continuation.resume(Unit)
            }
        }

        verify(mockArticlesDataSource, times(1)).getTopHeadlines()
    }

    @Test
    fun `test get top headlines from cache returns successful result`() =
        runBlockingTest(testDispatcher) {
            val articles = listOf(fixture<Article>())

            val mockArticlesDataSource = mock<ArticlesDataSource> {
                whenever(mock.getTopHeadlines())
                    .thenReturn(Result.Success(articles))
            }

            val articleRepository = DefaultArticleRepository(
                mockArticlesDataSource,
                testDispatcher
            )

            suspendAndAwait<Unit> { continuation ->
                articleRepository.getTopHeadLines().test(this) {
                    assertNoErrors()
                    assertNotComplete()
                    assertValue { it is Result.Success }
                    assertValue { (it as Result.Success).data.size == articles.size }
                    assertValue { (it as Result.Success).data.first().title == articles.first().title }
                    continuation.resume(Unit)
                }
            }

            suspendAndAwait<Unit> { continuation ->
                articleRepository.getTopHeadLines().test(this) {
                    assertNoErrors()
                    assertNotComplete()
                    assertValue { it is Result.Success }
                    assertValue { (it as Result.Success).data.size == articles.size }
                    assertValue { (it as Result.Success).data.first().title == articles.first().title }
                    continuation.resume(Unit)
                }
            }

            verify(mockArticlesDataSource, times(1)).getTopHeadlines()
        }

    @Test
    fun `test refresh top headlines returns successful result`() = runBlockingTest(testDispatcher) {
        val articles = listOf(fixture<Article>())

        val mockArticlesDataSource = mock<ArticlesDataSource> {
            whenever(mock.getTopHeadlines())
                .thenReturn(Result.Success(articles))
        }

        val articleRepository = DefaultArticleRepository(
            mockArticlesDataSource,
            testDispatcher
        )

        suspendAndAwait<Unit> { continuation ->
            articleRepository.getTopHeadLines().test(this) {
                assertNoErrors()
                assertNotComplete()
                assertValue { it is Result.Success }
                assertValue { (it as Result.Success).data.size == articles.size }
                assertValue { (it as Result.Success).data.first().title == articles.first().title }
                continuation.resume(Unit)
            }
        }

        val refreshResult = articleRepository.refreshTopHeadLines()
        assertTrue(refreshResult is Result.Success)

        suspendAndAwait<Unit> { continuation ->
            articleRepository.getTopHeadLines().test(this) {
                assertNoErrors()
                assertNotComplete()
                assertValue { it is Result.Success }
                assertValue { (it as Result.Success).data.size == articles.size }
                assertValue { (it as Result.Success).data.first().title == articles.first().title }
                continuation.resume(Unit)
            }
        }

        verify(mockArticlesDataSource, times(2)).getTopHeadlines()
    }
}