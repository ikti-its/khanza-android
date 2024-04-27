package dev.ikti.auth.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.auth.presentation.component.atom.LoginProgress
import dev.ikti.auth.presentation.component.molecule.LoginWelcomeText
import dev.ikti.auth.presentation.component.organism.LoginForm
import dev.ikti.auth.util.AuthConstant.ERR_ACCOUNT_UNAUTHORIZED
import dev.ikti.auth.util.AuthConstant.ERR_FAILED_TO_LOGIN
import dev.ikti.auth.util.AuthConstant.ERR_UNKNOWN_ERROR
import dev.ikti.auth.util.loginToast
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.core.util.UIState

@Composable
fun LoginContent(
    context: Context = LocalContext.current,
    modifier: Modifier,
    stateLogin: UIState<Unit>,
    userToken: String,
    onSubmit: (nip: String, password: String) -> Unit,
    navigateToMain: (token: String) -> Unit
) {
    MainScaffold(
        modifier = modifier,
        background = Color(0xFFF7F7F7)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 80.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                LoginWelcomeText()
                Spacer(modifier = modifier.size(60.dp))
                LoginForm { nip, password ->
                    onSubmit(nip, password)
                }
            }
        }
        stateLogin.let { state ->
            when (state) {
                is UIState.Success -> {
                    navigateToMain(userToken)
                }

                is UIState.Error -> {
                    when (state.error) {
                        ERR_ACCOUNT_UNAUTHORIZED -> {
                            loginToast(context = context, type = ERR_ACCOUNT_UNAUTHORIZED)
                        }

                        ERR_FAILED_TO_LOGIN -> {
                            loginToast(context = context, type = ERR_FAILED_TO_LOGIN)
                        }

                        else -> {
                            loginToast(context = context, type = ERR_UNKNOWN_ERROR)
                        }
                    }
                }

                UIState.Loading -> {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(Color(0xFF272727).copy(alpha = 0.25f))
                    ) {
                        LoginProgress()
                    }
                }

                UIState.Empty -> {}
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
            stateLogin = UIState.Empty,
            userToken = "",
            onSubmit = { _, _ -> },
            navigateToMain = { _ -> }
        )
    }
}
