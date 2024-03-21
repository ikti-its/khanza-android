package dev.ikti.auth.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ikti.core.util.State

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToMain: () -> Unit
) {
    val stateLogin by viewModel.stateLogin.collectAsState(initial = State.Empty)
    
    LoginContent(
        modifier = modifier,
        stateLogin = stateLogin,
        navigateBack = navigateBack,
        navigateToMain = navigateToMain,
        onSubmit = { nip, password ->
            viewModel.login(nip, password)
        }
    )
}
