package dev.ikti.auth.presentation.component.organism

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.auth.presentation.component.atom.LoginForgotText
import dev.ikti.auth.presentation.component.molecule.LoginFormField
import dev.ikti.auth.presentation.component.molecule.LoginSubmitButton
import dev.ikti.auth.util.AuthConstant.ERR_EMPTY_EMAIL
import dev.ikti.auth.util.AuthConstant.ERR_EMPTY_PASSWORD
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_EMAIL
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.auth.util.loginToast
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    onSubmit: (nip: String, password: String) -> Unit
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = modifier.fillMaxWidth()) {
            LoginFormField(field = FIELD_TYPE_EMAIL) { value, error ->
                username = value
                isUsernameError = error
            }
        }
        Spacer(modifier = modifier.size(5.dp))
        Row(modifier = modifier.fillMaxWidth()) {
            LoginFormField(field = FIELD_TYPE_PASSWORD) { value, error ->
                password = value
                isPasswordError = error
            }
        }
        Spacer(modifier = modifier.size(30.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            LoginForgotText()
        }
        Spacer(modifier = modifier.size(30.dp))
        Row(modifier = modifier.fillMaxWidth()) {
            LoginSubmitButton {
                if (username.isEmpty()) {
                    loginToast(context = context, type = ERR_EMPTY_EMAIL)
                } else if (password.isEmpty()) {
                    loginToast(context = context, type = ERR_EMPTY_PASSWORD)
                } else if (username.isNotEmpty() && password.isNotEmpty() && !isUsernameError && !isPasswordError) {
                    onSubmit(username, password)
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginFormPreview() {
    KhanzaTheme {
        LoginForm { _, _ -> }
    }
}
