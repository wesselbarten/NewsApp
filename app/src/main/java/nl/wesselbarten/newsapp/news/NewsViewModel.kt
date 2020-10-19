package nl.wesselbarten.newsapp.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.domain.repository.ArticleRepository
import nl.wesselbarten.newsapp.util.event.EmptyEvent

class NewsViewModel @ViewModelInject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    private val _getArticlesFailed = MutableLiveData<EmptyEvent>()
    val getArticlesFailed: LiveData<EmptyEvent> get() = _getArticlesFailed

    private val _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article> get() = _selectedArticle

    fun getTopHeadLines() {
        getTopHeadLines(false)
    }

    fun refreshTopHeadlines() {
        getTopHeadLines(true)
    }

    fun selectArticle(article: Article) {
        viewModelScope.launch {
            articleRepository.setViewedArticle(article)
        }
        _selectedArticle.value = article
    }

    private fun getTopHeadLines(forceUpdate: Boolean) {
        viewModelScope.launch {
            try {
                _articles.value = articleRepository.getTopHeadlines(forceUpdate)
            } catch (e: Exception) {
                _getArticlesFailed.value = EmptyEvent
            }
        }
    }
}