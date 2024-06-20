package dev.ikti.auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.auth.data.model.LoginRequest
import dev.ikti.auth.domain.usecase.LoginUseCase
import dev.ikti.core.domain.usecase.preference.ClearUserTokenUseCase
import dev.ikti.core.domain.usecase.preference.SetNewUserUseCase
import dev.ikti.core.domain.usecase.preference.SetUserTokenUseCase
import dev.ikti.core.util.NetworkConstant
import dev.ikti.core.util.NetworkException
import dev.ikti.core.util.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setNewUserUseCase: SetNewUserUseCase,
    private val setUserTokenUseCase: SetUserTokenUseCase,
    private val clearUserTokenUseCase: ClearUserTokenUseCase
) : ViewModel() {
    private val _stateLogin: MutableStateFlow<UIState<Unit>> =
        MutableStateFlow(UIState.Empty)
    val stateLogin: StateFlow<UIState<Unit>> = _stateLogin

    private val _userToken = mutableStateOf("")
    val userToken: State<String> get() = _userToken

    init {
        clearUserToken()
    }

    fun login(email: String, password: String) {
        _stateLogin.value = UIState.Loading
        viewModelScope.launch {
            try {
                val request = LoginRequest(email, password)
                val response = loginUseCase.execute(request)
                response.collect { token ->
                    setNewUser()
                    setUserTokenUseCase.execute(token.data.token)
                    _userToken.value = token.data.token
                }

                _stateLogin.value = UIState.Success(Unit)
            } catch (e: Exception) {
                when (e) {
                    NetworkException.UnauthorizedException, NetworkException.NotFoundException -> _stateLogin.value =
                        UIState.Error(NetworkConstant.ERR_UNAUTHORIZED)

                    else -> _stateLogin.value = UIState.Error(NetworkConstant.ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    private fun clearUserToken() {
        viewModelScope.launch {
            clearUserTokenUseCase.execute(Unit)
        }
    }

    private fun setNewUser() {
        viewModelScope.launch {
            setNewUserUseCase.execute(false)
        }
    }
}
