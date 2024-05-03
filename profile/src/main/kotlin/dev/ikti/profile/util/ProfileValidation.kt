package dev.ikti.profile.util

import android.util.Patterns

internal fun validateEmail(email: String): Boolean {
    return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

internal fun validateInput(text: String, minimum: Int, maximum: Int): Boolean {
    return text.length < minimum || text.length > maximum
}
