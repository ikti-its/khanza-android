package dev.ikti.auth.presentation.util

internal fun validateInput(text: String, limit: Int): Boolean {
    return text.length > limit
}
