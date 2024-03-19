package dev.ikti.core.data

sealed class UiState<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : UiState<T>()
    data class Error(val err: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}
