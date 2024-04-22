package dev.ikti.khanza.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.preference.ObserveIsNewUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val observeIsNewUserUseCase: ObserveIsNewUserUseCase
) : ViewModel() {
    private val _userToken = MutableLiveData<String>()
    val userToken: LiveData<String> = _userToken

    private val _isNewUser = MutableLiveData<Boolean>()
    val isNewUser: LiveData<Boolean> = _isNewUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getUserToken(Unit)
        observeIsNewUser(Unit)
    }

    private fun getUserToken(state: Unit) {
        viewModelScope.launch {
            getUserTokenUseCase.execute(state)
                .collect { token ->
                    _userToken.postValue(token)
                }
        }
    }

    private fun observeIsNewUser(state: Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            observeIsNewUserUseCase.execute(state)
                .collect { isNew ->
                    _isNewUser.postValue(isNew)
                    _isLoading.postValue(false)
                }
        }
    }
}