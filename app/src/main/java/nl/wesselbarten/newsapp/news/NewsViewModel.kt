package nl.wesselbarten.newsapp.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.domain.repository.ArticleRepository

class NewsViewModel @ViewModelInject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
             _articles.value = articleRepository.getTopHeadlines()
        }
    }
}