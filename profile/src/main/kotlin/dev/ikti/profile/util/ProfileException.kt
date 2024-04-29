package dev.ikti.profile.util

sealed class ProfileException(message: String) : Exception(message) {
    object AccountNotFoundException : ProfileException(ProfileConstant.ERR_ACCOUNT_NOT_FOUND)
}
