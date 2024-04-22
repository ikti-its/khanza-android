package dev.ikti.auth.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.auth.presentation.component.atom.LoginProgress
import dev.ikti.auth.presentation.component.atom.LoginToast
import dev.ikti.auth.presentation.component.molecule.LoginAppBar
import dev.ikti.auth.presentation.component.organism.LoginForm
import dev.ikti.auth.util.AuthConstant.ERR_ACCOUNT_UNAUTHORIZED
import dev.ikti.auth.util.AuthConstant.ERR_FAILED_TO_LOGIN
import dev.ikti.auth.util.AuthConstant.ERR_UNKNOWN_ERROR
import dev.ikti.core.presentation.component.template.MainScaffold
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
    MainScaffold(
        modifier = modifier,
        topBar = { LoginAppBar(navigateBack = navigateBack) },
        background = Color(0xFFF7F7F7)
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
                            ERR_ACCOUNT_UNAUTHORIZED -> {
                                LoginToast(context = context, type = ERR_ACCOUNT_UNAUTHORIZED)
                            }

                            ERR_FAILED_TO_LOGIN -> {
                                LoginToast(context = context, type = ERR_FAILED_TO_LOGIN)
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
