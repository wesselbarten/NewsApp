package nl.wesselbarten.newsapp.data.network

import android.net.Uri
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject

private const val PARAMETER_API_KEY = "apiKey"

class RestClient @Inject constructor(
    baseUrl: String,
    newsApiKey: String,
    enableLogging: Boolean,
    gson: Gson
) {

    private val retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder()
            .also {
                if (enableLogging) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    it.addInterceptor(loggingInterceptor)
                }
            }
            .build()

        val url = Uri.Builder()
            .path(baseUrl)
            .appendQueryParameter(PARAMETER_API_KEY, newsApiKey)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(url.toString())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getNewsApiService(): ArticleApiService {
        return retrofit.create()
    }
}