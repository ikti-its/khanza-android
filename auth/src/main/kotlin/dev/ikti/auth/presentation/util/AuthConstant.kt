package dev.ikti.auth.presentation.util

object AuthConstant {
    const val FIELD_TYPE_NIP = "nip"
    const val FIELD_TYPE_PASSWORD = "password"
    const val MAXIMUM_NIP_LENGTH = 5
    const val MAXIMUM_PASSWORD_LENGTH = 20

    //  Error
    const val ERR_ACCOUNT_NOT_FOUND = "ErrAccountNotFound"
    const val ERR_EMPTY_USERNAME = "ErrEmptyUsername"
    const val ERR_EMPTY_PASSWORD = "ErrEmptyPassword"
    const val ERR_FAILED_TO_LOGIN = "ErrFailedToLogin"
    const val ERR_FAILED_TO_SET_USER_TOKEN = "ErrFailedToSetUserToken"
    const val ERR_PASSWORD_INCORRECT = "ErrPasswordIncorrect"
    const val ERR_UNKNOWN_ERROR = "ErrUnknownError"
}
