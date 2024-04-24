package dev.ikti.core.util

sealed class UIState<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : UIState<T>()
    data class Error(val error: String) : UIState<Nothing>()
    object Loading : UIState<Nothing>()

    object Empty : UIState<Nothing>()
}
