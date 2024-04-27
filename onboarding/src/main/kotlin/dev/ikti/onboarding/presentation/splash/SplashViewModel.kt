package dev.ikti.onboarding.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.preference.ObserveIsNewUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val observeIsNewUserUseCase: ObserveIsNewUserUseCase
) : ViewModel() {
    private val _userToken = MutableStateFlow("")
    val userToken: StateFlow<String> = _userToken

    private val _isNewUser = MutableStateFlow(false)
    val isNewUser: StateFlow<Boolean> = _isNewUser

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getUserToken(Unit)
    }

    private fun getUserToken(state: Unit) {
        viewModelScope.launch {
            getUserTokenUseCase.execute(state)
                .collect { token ->
                    _userToken.value = token
                }
        }
    }

    fun observeIsNewUser(state: Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            observeIsNewUserUseCase.execute(state)
                .collect { isNew ->
                    _isNewUser.value = isNew
                    _isLoading.value = false
                }
        }
    }
}