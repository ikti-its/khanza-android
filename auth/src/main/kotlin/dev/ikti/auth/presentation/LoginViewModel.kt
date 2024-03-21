package dev.ikti.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.auth.data.model.LoginRequest
import dev.ikti.auth.domain.usecase.LoginUseCase
import dev.ikti.auth.presentation.util.AuthConstant.ERR_ACCOUNT_NOT_FOUND
import dev.ikti.auth.presentation.util.AuthConstant.ERR_FAILED_TO_LOGIN
import dev.ikti.auth.presentation.util.AuthConstant.ERR_FAILED_TO_SET_USER_TOKEN
import dev.ikti.auth.presentation.util.AuthConstant.ERR_PASSWORD_INCORRECT
import dev.ikti.auth.presentation.util.AuthConstant.ERR_UNKNOWN_ERROR
import dev.ikti.auth.presentation.util.AuthException
import dev.ikti.core.domain.usecase.local.preference.SetUserTokenUseCase
import dev.ikti.core.util.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setUserTokenUseCase: SetUserTokenUseCase
) : ViewModel() {
    private val _stateLogin: MutableStateFlow<State<Unit>> =
        MutableStateFlow(State.Empty)
    val stateLogin: StateFlow<State<Unit>> = _stateLogin

    fun login(email: String, password: String) {
        _stateLogin.value = State.Loading

        val request = LoginRequest(email, password)
        viewModelScope.launch {
            try {
                val response = loginUseCase.execute(request)
                response.collect { token ->
                    try {
                        setUserTokenUseCase.execute(token.token ?: "")
                    } catch (_: Exception) {
                        _stateLogin.value = State.Error(ERR_FAILED_TO_SET_USER_TOKEN)
                    }
                }

                _stateLogin.value = State.Success(Unit)
            } catch (e: Exception) {
                when (e) {
                    AuthException.AccountNotFoundException -> _stateLogin.value =
                        State.Error(ERR_ACCOUNT_NOT_FOUND)

                    AuthException.PasswordIncorrectException -> _stateLogin.value =
                        State.Error(ERR_PASSWORD_INCORRECT)

                    AuthException.FailedToLoginException -> _stateLogin.value =
                        State.Error(ERR_FAILED_TO_LOGIN)

                    else -> _stateLogin.value = State.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }
}
