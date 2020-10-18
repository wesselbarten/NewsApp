package nl.wesselbarten.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class SourceDataModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String
)