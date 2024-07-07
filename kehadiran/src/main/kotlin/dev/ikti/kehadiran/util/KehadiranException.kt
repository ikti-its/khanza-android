package dev.ikti.kehadiran.util

import dev.ikti.kehadiran.util.KehadiranConstant.ERR_ACCOUNT_NOT_FOUND

sealed class KehadiranException(message: String) : Exception(message) {
    object AccountNotFoundException : KehadiranException(ERR_ACCOUNT_NOT_FOUND)
}
