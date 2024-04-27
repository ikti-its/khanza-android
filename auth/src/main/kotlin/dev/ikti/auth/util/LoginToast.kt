package dev.ikti.auth.util

import android.content.Context
import android.widget.Toast
import dev.ikti.auth.util.AuthConstant.ERR_ACCOUNT_UNAUTHORIZED
import dev.ikti.auth.util.AuthConstant.ERR_EMPTY_EMAIL
import dev.ikti.auth.util.AuthConstant.ERR_EMPTY_PASSWORD
import dev.ikti.auth.util.AuthConstant.ERR_FAILED_TO_LOGIN
import dev.ikti.auth.util.AuthConstant.ERR_FAILED_TO_SET_USER_TOKEN

fun loginToast(
    context: Context,
    type: String
) {
    Toast.makeText(
        context,
        when (type) { // TODO: i18n
            ERR_ACCOUNT_UNAUTHORIZED -> "Email/password tidak sesuai"
            ERR_EMPTY_EMAIL -> "Email tidak boleh kosong"
            ERR_EMPTY_PASSWORD -> "Password tidak boleh kosong"
            ERR_FAILED_TO_LOGIN -> "Gagal login"
            ERR_FAILED_TO_SET_USER_TOKEN -> "Gagal menyimpan token pengguna"
            else -> "Terjadi kesalahan"
        },
        Toast.LENGTH_SHORT
    ).show()
}