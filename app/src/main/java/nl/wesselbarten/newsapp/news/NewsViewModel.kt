package nl.wesselbarten.newsapp.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.wesselbarten.newsapp.domain.model.NewsItem
import nl.wesselbarten.newsapp.domain.repository.NewsItemRepository

class NewsViewModel(
    private val newsItemsRepository: NewsItemRepository
) : ViewModel() {

    private val _newsItems = MutableLiveData<List<NewsItem>>()
    val newsItems: LiveData<List<NewsItem>> get() = _newsItems
}