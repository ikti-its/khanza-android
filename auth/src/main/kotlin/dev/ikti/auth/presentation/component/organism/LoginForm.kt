package dev.ikti.auth.presentation.component.organism

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.auth.presentation.component.atom.LoginToast
import dev.ikti.auth.presentation.component.molecule.LoginFormField
import dev.ikti.auth.presentation.component.molecule.LoginSubmitButton
import dev.ikti.auth.presentation.util.AuthConstant.ERR_EMPTY_PASSWORD
import dev.ikti.auth.presentation.util.AuthConstant.ERR_EMPTY_USERNAME
import dev.ikti.auth.presentation.util.AuthConstant.FIELD_TYPE_NIP
import dev.ikti.auth.presentation.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun LoginForm(
    context: Context = LocalContext.current,
    modifier: Modifier,
    onSubmit: (nip: String, password: String) -> Unit
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            LoginFormField(
                modifier = modifier,
                field = FIELD_TYPE_NIP,
                onValueChange = { value, error ->
                    username = value
                    isUsernameError = error
                }
            )
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            LoginFormField(
                modifier = modifier,
                field = FIELD_TYPE_PASSWORD,
                onValueChange = { value, error ->
                    password = value
                    isPasswordError = error
                }
            )
        }

        Spacer(modifier = modifier.padding(vertical = 72.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            LoginSubmitButton(
                modifier = modifier,
                onSubmit = {
                    if (username.isEmpty()) {
                        LoginToast(context = context, type = ERR_EMPTY_USERNAME)
                    } else if (password.isEmpty()) {
                        LoginToast(context = context, type = ERR_EMPTY_PASSWORD)
                    } else if (username.isNotEmpty() && password.isNotEmpty() && !isUsernameError && !isPasswordError) {
                        onSubmit(username, password)
                    }
                },
            )
        }
    }
}

@Preview
@Composable
fun LoginFormPreview() {
    KhanzaTheme {
        LoginForm(
            modifier = Modifier,
            onSubmit = { _, _ -> }
        )
    }
}
