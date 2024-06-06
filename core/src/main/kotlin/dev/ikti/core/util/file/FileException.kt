package dev.ikti.core.util.file

sealed class FileException(message: String) : Exception(message) {
    object UnauthorizedException : FileException(FileConstant.ERR_UNAUTHORIZED)
    object UnsupportedException : FileException(FileConstant.ERR_UNSUPPORTED)
}
