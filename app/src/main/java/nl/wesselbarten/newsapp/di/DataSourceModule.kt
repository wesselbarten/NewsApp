package nl.wesselbarten.newsapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import nl.wesselbarten.newsapp.data.network.ArticleApiService
import nl.wesselbarten.newsapp.data.source.ArticlesDataSource
import nl.wesselbarten.newsapp.data.source.articles.RemoteArticlesDataSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideArticlesDataSource(articleApiService: ArticleApiService): ArticlesDataSource {
        return RemoteArticlesDataSource(articleApiService)
    }
}