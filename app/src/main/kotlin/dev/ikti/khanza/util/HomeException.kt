package dev.ikti.khanza.util

sealed class HomeException(message: String) : Exception(message) {
    object AccountNotFoundException : HomeException(HomeConstant.ERR_ACCOUNT_NOT_FOUND)
    object AccountUnauthorizedException : HomeException(HomeConstant.ERR_ACCOUNT_UNAUTHORIZED)
    object QueryInvalidException : HomeException(HomeConstant.ERR_QUERY_INVALID)
}
