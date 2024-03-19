package dev.ikti.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.data.UiState
import dev.ikti.core.data.model.auth.LoginRequest
import dev.ikti.core.data.model.auth.LoginResponse
import dev.ikti.core.domain.usecase.remote.auth.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiStateLogin: MutableStateFlow<UiState<LoginResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiStateLogin: StateFlow<UiState<LoginResponse>> = _uiStateLogin

    fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        viewModelScope.launch {
            try {
                loginUseCase.execute(request)
                    .catch {
                        _uiStateLogin.value = UiState.Error(it.message ?: "Unknown error")
                    }
                    .collect {
                        _uiStateLogin.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                _uiStateLogin.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
