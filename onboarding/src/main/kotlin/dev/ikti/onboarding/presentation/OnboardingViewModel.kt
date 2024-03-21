package dev.ikti.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.usecase.preference.SetNewUserUseCase
import dev.ikti.core.util.State
import dev.ikti.onboarding.util.OnboardingConstant.ERR_FAILED_TO_SET_USER
import dev.ikti.onboarding.util.OnboardingConstant.ERR_UNKNOWN_ERROR
import dev.ikti.onboarding.util.OnboardingException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setNewUserUseCase: SetNewUserUseCase
) : ViewModel() {
    private val _stateOnboarding: MutableStateFlow<State<Boolean>> = MutableStateFlow(State.Empty)
    val stateOnboarding: MutableStateFlow<State<Boolean>> = _stateOnboarding

    fun setNewUser(state: Boolean) {
        _stateOnboarding.value = State.Loading

        viewModelScope.launch {
            try {
                setNewUserUseCase.execute(state)
                _stateOnboarding.value = State.Success(true)
            } catch (e: Exception) {
                when (e) {
                    is OnboardingException.FailedToSetUserException -> _stateOnboarding.value =
                        State.Error(ERR_FAILED_TO_SET_USER)

                    else -> _stateOnboarding.value = State.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }
}