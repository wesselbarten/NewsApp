package nl.wesselbarten.newsapp.data.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalRequest = chain.request()
                    val url = originalRequest.url.newBuilder()
                        .addQueryParameter(PARAMETER_API_KEY, newsApiKey)
                        .build()

                    val request = originalRequest.newBuilder()
                        .url(url)
                        .header("Content-Type", "application/json")
                        .build()

                    return chain.proceed(request)
                }
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getNewsApiService(): ArticleApiService {
        return retrofit.create()
    }
}