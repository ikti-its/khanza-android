package dev.ikti.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.usecase.local.preference.SetNewUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setNewUserUseCase: SetNewUserUseCase
) : ViewModel() {
    fun setNewUser(state: Boolean) {
        viewModelScope.launch {
            setNewUserUseCase.execute(state)
        }
    }
}