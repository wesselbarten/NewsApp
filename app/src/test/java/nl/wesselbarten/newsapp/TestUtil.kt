package nl.wesselbarten.newsapp

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

suspend inline fun <T> suspendAndAwait(crossinline continuation: suspend (Continuation<T>) -> Unit): T {
    return coroutineScope {
        suspendCoroutine {
            launch {
                continuation.invoke(it)
            }
        }
    }
}