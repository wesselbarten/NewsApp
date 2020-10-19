package nl.wesselbarten.newsapp.data.repository

import com.appmattus.kotlinfixture.kotlinFixture
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.domain.model.Article
import org.junit.Assert.assertEquals
import org.junit.Test

@Suppress("EXPERIMENTAL_API_USAGE")
class DefaultArticleRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val fixture = kotlinFixture()

    @Test
    fun `test get top headlines is successful`() = runBlockingTest {
        val articles = listOf(fixture<Article>())

        val mockArticlesDataSource = mock<ArticlesDataSource> {
            whenever(mock.getTopHeadlines())
                .thenReturn(articles)
        }

        val articleRepository = DefaultArticleRepository(
            mockArticlesDataSource,
            testDispatcher
        )

        val articlesResult = articleRepository.getTopHeadlines(false)

        assertEquals(articles.size, articlesResult.size)
        assertEquals(articles.first().title, articlesResult.first().title)
        verify(mockArticlesDataSource, times(1)).getTopHeadlines()
    }

    @Test(expected = Exception::class)
    fun `test get top headlines throws exception`() = runBlockingTest {
        val mockArticlesDataSource = mock<ArticlesDataSource> {
            whenever(mock.getTopHeadlines())
                .thenThrow(Exception())
        }

        val articleRepository = DefaultArticleRepository(
            mockArticlesDataSource,
            testDispatcher
        )

        articleRepository.getTopHeadlines(false)

        verify(mockArticlesDataSource, times(1)).getTopHeadlines()
    }

    @Test
    fun `test get top headlines from cache is successful`() = runBlockingTest {
        val articles = listOf(fixture<Article>())

        val mockArticlesDataSource = mock<ArticlesDataSource> {
            whenever(mock.getTopHeadlines())
                .thenReturn(articles)
        }

        val articleRepository = DefaultArticleRepository(
            mockArticlesDataSource,
            testDispatcher
        )

        val articlesFirstResult = articleRepository.getTopHeadlines(false)
        assertEquals(articles.size, articlesFirstResult.size)
        assertEquals(articles.first().title, articlesFirstResult.first().title)

        val articlesSecondResult = articleRepository.getTopHeadlines(false)
        assertEquals(articles.size, articlesSecondResult.size)
        assertEquals(articles.first().title, articlesSecondResult.first().title)

        verify(mockArticlesDataSource, times(1)).getTopHeadlines()
    }

    @Test
    fun `test force update get top headlines is successful`() = runBlockingTest {
        val articles = listOf(fixture<Article>())

        val mockArticlesDataSource = mock<ArticlesDataSource> {
            whenever(mock.getTopHeadlines())
                .thenReturn(articles)
        }

        val articleRepository = DefaultArticleRepository(
            mockArticlesDataSource,
            testDispatcher
        )

        val articlesFirstResult = articleRepository.getTopHeadlines(false)
        assertEquals(articles.size, articlesFirstResult.size)
        assertEquals(articles.first().title, articlesFirstResult.first().title)

        val articlesSecondResult = articleRepository.getTopHeadlines(true)
        assertEquals(articles.size, articlesSecondResult.size)
        assertEquals(articles.first().title, articlesSecondResult.first().title)

        verify(mockArticlesDataSource, times(2)).getTopHeadlines()
    }
}