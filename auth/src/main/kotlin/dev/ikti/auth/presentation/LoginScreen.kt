package dev.ikti.auth.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ikti.core.util.UIState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToMain: (token: String) -> Unit
) {
    val stateLogin by viewModel.stateLogin.collectAsState(initial = UIState.Empty)
    val token by viewModel.userToken

    LoginContent(
        modifier = modifier,
        stateLogin = stateLogin,
        userToken = token,
        navigateToMain = navigateToMain,
        onSubmit = { nip, password ->
            viewModel.login(nip, password)
        }
    )
}
