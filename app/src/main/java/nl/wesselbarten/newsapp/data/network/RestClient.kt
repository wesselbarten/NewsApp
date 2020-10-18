package nl.wesselbarten.newsapp.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RestClient(
    baseUrl: String,
    enableLogging: Boolean,
    gson: Gson
) {

    private val retrofit: Retrofit

    init {
        val httpClientBuilder = OkHttpClient.Builder()

        if (enableLogging) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getNewsApiService(): NewsApiService {
        return retrofit.create()
    }
}