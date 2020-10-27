package nl.wesselbarten.newsapp.presentation.news

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
import nl.wesselbarten.newsapp.presentation.error.ErrorHandler
import nl.wesselbarten.newsapp.util.event.Event

class NewsViewModel @ViewModelInject constructor(
    private val articleRepository: ArticleRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _getTopHeadLinesState = MutableLiveData<GetTopHeadLinesState>()
    val getTopHeadLinesState: LiveData<GetTopHeadLinesState> get() = _getTopHeadLinesState

    private val _refreshTopHeadLinesAction = MutableLiveData<Event<RefreshTopHeadLinesAction>>()
    val refreshTopHeadLinesAction: MutableLiveData<Event<RefreshTopHeadLinesAction>> get() = _refreshTopHeadLinesAction

    private val _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article> get() = _selectedArticle

    init {
        viewModelScope.launch {
            articleRepository.getTopHeadLines()
                .collectLatest {
                    when (it) {
                        is Result.Success -> {
                            _getTopHeadLinesState.value = GetTopHeadLinesState.Success(it.data)
                        }
                        is Result.Error -> {
                            _getTopHeadLinesState.value = GetTopHeadLinesState.Error
                        }
                    }
                }
        }
    }

    fun refreshTopHeadlines() {
        viewModelScope.launch {
            val action = when (articleRepository.refreshTopHeadLines()) {
                is Result.Success -> RefreshTopHeadLinesAction.Success
                is Result.Error -> {
                    val errorMessage = errorHandler.getErrorMessage(ErrorHandler.REFRESH_TOP_HEADLINES_FAILED)
                    RefreshTopHeadLinesAction.Error(errorMessage)
                }
            }
            _refreshTopHeadLinesAction.value = Event(action)
        }
    }

    fun selectArticle(article: Article) {
        viewModelScope.launch {
            articleRepository.setViewedArticle(article)
        }
        _selectedArticle.value = article
    }
}