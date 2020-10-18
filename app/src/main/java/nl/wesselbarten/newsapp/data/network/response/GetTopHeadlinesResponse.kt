package nl.wesselbarten.newsapp.data.network.response

import com.google.gson.annotations.SerializedName
import nl.wesselbarten.newsapp.data.model.ArticleDataModel

data class GetTopHeadlinesResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<ArticleDataModel>
)