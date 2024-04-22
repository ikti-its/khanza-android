package dev.ikti.auth.util

import android.util.Patterns

internal fun validateEmail(email: String): Boolean {
    return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

internal fun validateInput(text: String, limit: Int): Boolean {
    return text.length > limit
}
