package dev.ikti.auth.util

internal fun validateInput(text: String, limit: Int): Boolean {
    return text.length > limit
}
