package dev.ikti.auth.presentation.component.atom

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import dev.ikti.auth.presentation.util.AuthConstant.ERR_ACCOUNT_NOT_FOUND
import dev.ikti.auth.presentation.util.AuthConstant.ERR_FAILED_TO_LOGIN
import dev.ikti.auth.presentation.util.AuthConstant.ERR_FAILED_TO_SET_USER_TOKEN
import dev.ikti.auth.presentation.util.AuthConstant.ERR_PASSWORD_INCORRECT

@Composable
fun LoginToast(
    context: Context,
    type: String
) {
    Toast.makeText(
        context,
        when (type) { // TODO: i18n
            ERR_ACCOUNT_NOT_FOUND -> "Akun tidak ditemukan"
            ERR_FAILED_TO_LOGIN -> "Gagal login"
            ERR_FAILED_TO_SET_USER_TOKEN -> "Gagal menyimpan token pengguna"
            ERR_PASSWORD_INCORRECT -> "Password salah"
            else -> "Terjadi kesalahan"
        },
        Toast.LENGTH_SHORT
    ).show()
}