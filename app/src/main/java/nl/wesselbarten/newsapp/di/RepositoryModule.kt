package nl.wesselbarten.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import nl.wesselbarten.newsapp.data.repository.DefaultArticleRepository
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.domain.repository.ArticleRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideArticleRepository(articlesDataSource: ArticlesDataSource): ArticleRepository {
        return DefaultArticleRepository(articlesDataSource)
    }
}