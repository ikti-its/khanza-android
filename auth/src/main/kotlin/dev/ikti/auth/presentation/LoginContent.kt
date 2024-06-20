package dev.ikti.auth.presentation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.core.util.NetworkConstant
import dev.ikti.core.util.UIState
import dev.ikti.core.util.showToast

@Composable
fun LoginContent(
    context: Context = LocalContext.current,
    stateLogin: UIState<Unit>,
    onSubmit: (String, String) -> Unit,
    navigateToMain: () -> Unit
) {
    MainScaffold(background = Color(0xFFF7F7F7)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 80.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LoginWelcomeText()
                Spacer(Modifier.height(60.dp))
                LoginForm { nip, password ->
                    onSubmit(nip, password)
                }
            }
        }
    }

    when (stateLogin) {
        is UIState.Success -> {
            navigateToMain()
        }

        is UIState.Error -> {
            when (stateLogin.error) {
                NetworkConstant.ERR_UNAUTHORIZED -> {
                    showToast(
                        context,
                        "Email/password tidak sesuai"
                    )
                }

                else -> {
                    showToast(
                        context,
                        "Terjadi kesalahan"
                    )
                }
            }
        }

        UIState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF272727).copy(alpha = 0.25f))
            ) {
                LoginProgress()
            }
        }

        UIState.Empty -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    KhanzaTheme {
        LoginContent(
            stateLogin = UIState.Empty,
            onSubmit = { _, _ -> },
            navigateToMain = {}
        )
    }
}
