package nl.wesselbarten.newsapp.data

/**
 * Adapted from https://github.com/android/architecture-samples.
 *
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

suspend fun <T> executeApiCall(action: suspend () -> T ): Result<T> {
    return try {
        Result.Success(action())
    } catch (e: Exception) {
        Result.Error(e)
    }
}

suspend fun <T, R> Result<T>.onResultSuccess(action: suspend (T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(action(this.data))
        is Result.Error -> Result.Error(this.exception)
    }
}