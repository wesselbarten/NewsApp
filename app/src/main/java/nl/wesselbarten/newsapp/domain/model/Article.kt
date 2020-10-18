package nl.wesselbarten.newsapp.domain.model

data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val imageUrl: String?,
    val publishedAt: String,
    val content: String?
) {

    fun areContentsTheSame(other: Article): Boolean {
        return author == other.author &&
                description == other.description &&
                publishedAt == other.publishedAt &&
                content == other.content
    }
}