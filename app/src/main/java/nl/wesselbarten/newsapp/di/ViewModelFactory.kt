package nl.wesselbarten.newsapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.wesselbarten.newsapp.domain.repository.ArticleRepository
import nl.wesselbarten.newsapp.news.NewsViewModel

class ViewModelFactory constructor(
    private val articleRepository: ArticleRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel =
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                NewsViewModel(articleRepository)
            }
            else throw RuntimeException("Unknown model class ${modelClass.simpleName}.")

        return viewModel as T
    }
}