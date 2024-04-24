package dev.ikti.auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.auth.data.model.LoginRequest
import dev.ikti.auth.domain.usecase.LoginUseCase
import dev.ikti.auth.util.AuthConstant.ERR_ACCOUNT_UNAUTHORIZED
import dev.ikti.auth.util.AuthConstant.ERR_FAILED_TO_LOGIN
import dev.ikti.auth.util.AuthConstant.ERR_FAILED_TO_SET_USER_TOKEN
import dev.ikti.auth.util.AuthConstant.ERR_UNKNOWN_ERROR
import dev.ikti.auth.util.AuthException
import dev.ikti.core.domain.usecase.preference.ClearUserTokenUseCase
import dev.ikti.core.domain.usecase.preference.SetUserTokenUseCase
import dev.ikti.core.util.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setUserTokenUseCase: SetUserTokenUseCase,
    private val clearUserTokenUseCase: ClearUserTokenUseCase
) : ViewModel() {
    private val _stateLogin: MutableStateFlow<UIState<Unit>> =
        MutableStateFlow(UIState.Empty)
    val stateLogin: StateFlow<UIState<Unit>> = _stateLogin

    private val _userToken = mutableStateOf("")
    val userToken: State<String> get() = _userToken

    init {
        clearUserToken(Unit)
    }

    fun login(email: String, password: String) {
        _stateLogin.value = UIState.Loading

        val request = LoginRequest(email, password)
        viewModelScope.launch {
            try {
                val response = loginUseCase.execute(request)
                response.collect { token ->
                    try {
                        setUserTokenUseCase.execute(token.data.token)
                        _userToken.value = token.data.token
                    } catch (_: Exception) {
                        _stateLogin.value = UIState.Error(ERR_FAILED_TO_SET_USER_TOKEN)
                    }
                }

                _stateLogin.value = UIState.Success(Unit)
            } catch (e: Exception) {
                when (e) {
                    AuthException.EmailInvalidException, AuthException.AccountNotFoundException, AuthException.PasswordIncorrectException -> _stateLogin.value =
                        UIState.Error(ERR_ACCOUNT_UNAUTHORIZED)

                    AuthException.FailedToLoginException -> _stateLogin.value =
                        UIState.Error(ERR_FAILED_TO_LOGIN)

                    else -> _stateLogin.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    private fun clearUserToken(state: Unit) {
        viewModelScope.launch {
            clearUserTokenUseCase.execute(state)
        }
    }
}
