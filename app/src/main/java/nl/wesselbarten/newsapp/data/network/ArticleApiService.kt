package nl.wesselbarten.newsapp.data.network

import nl.wesselbarten.newsapp.data.network.response.GetTopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiService {

    @GET("top-headlines")
    suspend fun getTopHeadLines(@Query("country") country: String): GetTopHeadlinesResponse
}