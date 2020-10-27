package nl.wesselbarten.newsapp.presentation.news

import nl.wesselbarten.newsapp.domain.model.Article

sealed class GetTopHeadLinesState {
    object Loading : GetTopHeadLinesState()
    class Success(val articles: List<Article>) : GetTopHeadLinesState()
    object Error : GetTopHeadLinesState()
}