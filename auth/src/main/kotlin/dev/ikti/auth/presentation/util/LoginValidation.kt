package dev.ikti.auth.presentation.util

fun ValidateInput(text: String, limit: Int): Boolean {
    return text.length > limit
}
