package dev.ikti.auth.util

import dev.ikti.auth.util.AuthConstant.ERR_ACCOUNT_NOT_FOUND
import dev.ikti.auth.util.AuthConstant.ERR_FAILED_TO_LOGIN
import dev.ikti.auth.util.AuthConstant.ERR_PASSWORD_INCORRECT

sealed class AuthException(message: String) : Exception(message) {
    object AccountNotFoundException : AuthException(ERR_ACCOUNT_NOT_FOUND)
    object FailedToLoginException : AuthException(ERR_FAILED_TO_LOGIN)
    object PasswordIncorrectException : AuthException(ERR_PASSWORD_INCORRECT)
}
