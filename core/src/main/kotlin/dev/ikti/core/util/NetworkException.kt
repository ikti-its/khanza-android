package dev.ikti.core.util

sealed class NetworkException(message: String) : Exception(message) {
    object BadRequestException : NetworkException(NetworkConstant.ERR_BAD_REQUEST)
    object UnauthorizedException : NetworkException(NetworkConstant.ERR_UNAUTHORIZED)
    object NotFoundException : NetworkException(NetworkConstant.ERR_NOT_FOUND)
    object FileUnsupportedException : NetworkException(NetworkConstant.ERR_FILE_UNSUPPORTED)
    object UnknownHostException : NetworkException(NetworkConstant.ERR_UNKNOWN_HOST)
}
