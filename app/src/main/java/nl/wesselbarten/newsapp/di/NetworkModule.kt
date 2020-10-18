package nl.wesselbarten.newsapp.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import nl.wesselbarten.newsapp.BuildConfig
import nl.wesselbarten.newsapp.data.network.ArticleApiService
import nl.wesselbarten.newsapp.data.network.RestClient
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRestClient(gson: Gson): RestClient {
        return RestClient(
            BuildConfig.NEWS_API_URL,
            BuildConfig.NEWS_API_KEY,
            BuildConfig.REQUEST_LOGGING_ENABLED,
            gson
        )
    }

    @Provides
    @Singleton
    fun provideArticleApiService(restClient: RestClient): ArticleApiService {
        return restClient.getNewsApiService()
    }
}