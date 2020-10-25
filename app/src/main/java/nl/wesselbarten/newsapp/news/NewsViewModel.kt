package nl.wesselbarten.newsapp.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nl.wesselbarten.newsapp.data.Result
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

    init {
        viewModelScope.launch {
            articleRepository.getTopHeadLines()
                .collectLatest {
                    when (it) {
                        is Result.Success -> {
                            _articles.value = it.data
                        }
                        is Result.Error -> {
                            _getArticlesFailed.value = EmptyEvent
                        }
                    }
                }
        }
    }

    fun refreshTopHeadlines() {
        viewModelScope.launch {
            try {
                articleRepository.refreshTopHeadLines()
            } catch (e: Exception) {
                _getArticlesFailed.value = EmptyEvent
            }
        }
    }

    fun selectArticle(article: Article) {
        viewModelScope.launch {
            articleRepository.setViewedArticle(article)
        }
        _selectedArticle.value = article
    }
}