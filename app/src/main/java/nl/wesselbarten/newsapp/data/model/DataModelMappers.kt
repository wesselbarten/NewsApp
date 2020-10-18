package nl.wesselbarten.newsapp.data.model

import nl.wesselbarten.newsapp.domain.model.Article
import nl.wesselbarten.newsapp.domain.model.Source

fun ArticleDataModel.toDomain(): Article {
    return Article(
        source.toDomain(),
        author,
        title,
        description,
        url,
        imageUrl,
        publishedAt,
        content
    )
}

fun SourceDataModel.toDomain(): Source {
    return Source(id, name)
}