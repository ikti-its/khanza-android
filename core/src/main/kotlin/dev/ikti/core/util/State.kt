package dev.ikti.core.util

sealed class State<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : State<T>()
    data class Error(val error: String) : State<Nothing>()
    object Loading : State<Nothing>()

    object Empty : State<Nothing>()
}
