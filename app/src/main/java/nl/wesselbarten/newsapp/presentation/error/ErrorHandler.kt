package nl.wesselbarten.newsapp.presentation.error

import android.content.res.Resources
import nl.wesselbarten.newsapp.R

class ErrorHandler(
    private val resources: Resources
) {

    private val errorCodeMessageMap: Map<Int, Int>

    init {
        errorCodeMessageMap = mapOf(
            REFRESH_TOP_HEADLINES_FAILED to R.string.error_unable_to_get_articles
        )
    }

    fun getErrorMessage(errorCode: Int): String {
        val errorId = errorCodeMessageMap[errorCode]
            ?: throw IllegalArgumentException("There is no error message available for error code: $errorCode.")
        return resources.getString(errorId)
    }

    companion object ErrorCodes {
        const val REFRESH_TOP_HEADLINES_FAILED = 1
    }
}