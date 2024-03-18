package dev.ikti.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.service.local.preference.SetNewUserService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setNewUserService: SetNewUserService
) : ViewModel() {
    fun setNewUser(state: Boolean) {
        viewModelScope.launch {
            setNewUserService.execute(state)
        }
    }
}