package dev.ikti.pegawai.util

import dev.ikti.pegawai.util.PegawaiConstant.ERR_ACCOUNT_NOT_FOUND

sealed class PegawaiException(message: String) : Exception(message) {
    object AccountNotFoundException : PegawaiException(ERR_ACCOUNT_NOT_FOUND)
}
