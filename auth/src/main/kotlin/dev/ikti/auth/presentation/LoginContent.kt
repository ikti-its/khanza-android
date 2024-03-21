package dev.ikti.auth.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.presentation.component.atom.LoginProgress
import dev.ikti.auth.presentation.component.atom.LoginToast
import dev.ikti.auth.presentation.component.molecule.LoginAppBar
import dev.ikti.auth.presentation.component.organism.LoginForm
import dev.ikti.auth.presentation.util.AuthConstant.ERR_ACCOUNT_NOT_FOUND
import dev.ikti.auth.presentation.util.AuthConstant.ERR_FAILED_TO_LOGIN
import dev.ikti.auth.presentation.util.AuthConstant.ERR_FAILED_TO_SET_USER_TOKEN
import dev.ikti.auth.presentation.util.AuthConstant.ERR_PASSWORD_INCORRECT
import dev.ikti.auth.presentation.util.AuthConstant.ERR_UNKNOWN_ERROR
import dev.ikti.core.presentation.theme.KhanzaDark
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.core.util.State

@Composable
fun LoginContent(
    context: Context = LocalContext.current,
    modifier: Modifier,
    stateLogin: State<Unit>,
    onSubmit: (nip: String, password: String) -> Unit,
    navigateBack: () -> Unit,
    navigateToMain: () -> Unit
) {
    Scaffold(
        topBar = {
            LoginAppBar(navigateBack = navigateBack)
        }
    ) {
        Surface(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            color = KhanzaDark
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                LoginForm(
                    modifier = modifier,
                    onSubmit = onSubmit
                )

                stateLogin.let { state ->
                    when (state) {
                        is State.Success -> {
                            navigateToMain()
                        }

                        is State.Error -> {
                            when (state.error) {
                                ERR_ACCOUNT_NOT_FOUND -> {
                                    Toast.makeText(
                                        LocalContext.current,
                                        "Akun tidak ditemukan",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                ERR_FAILED_TO_SET_USER_TOKEN -> {
                                    LoginToast(context = context, type = ERR_FAILED_TO_SET_USER_TOKEN)
                                }

                                ERR_FAILED_TO_LOGIN -> {
                                    LoginToast(context = context, type = ERR_FAILED_TO_LOGIN)
                                }

                                ERR_PASSWORD_INCORRECT -> {
                                    LoginToast(context = context, type = ERR_PASSWORD_INCORRECT)
                                }

                                else -> {
                                    LoginToast(context = context, type = ERR_UNKNOWN_ERROR)
                                }
                            }
                        }

                        State.Loading -> {
                            Box(
                                modifier = modifier
                                    .fillMaxSize()
                                    .background(KhanzaDark.copy(alpha = 0.5f))
                            ) {
                                LoginProgress(modifier = modifier)
                            }
                        }

                        State.Empty -> {}
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    KhanzaTheme {
        LoginContent(
            modifier = Modifier,
            stateLogin = State.Empty,
            onSubmit = { _, _ -> },
            navigateBack = {},
            navigateToMain = {}
        )
    }
}
