package dev.ikti.home.util

sealed class HomeException(message: String) : Exception(message) {
    object AccountUnauthorizedException : HomeException(HomeConstant.ERR_ACCOUNT_UNAUTHORIZED)
    object AccountNotFoundException : HomeException(HomeConstant.ERR_ACCOUNT_NOT_FOUND)
}
