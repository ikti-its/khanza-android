package dev.ikti.kehadiran.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class KehadiranViewModel @Inject constructor(
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase
) : ViewModel() {
    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _pegawai = MutableStateFlow("")
    val pegawai: StateFlow<String> = _pegawai

    init {
        getUserToken()
    }

    private fun getUserToken() {
        viewModelScope.launch {
            getUserTokenUseCase.execute(Unit)
                .collect { token ->
                    _token.value = token
                }
        }
    }

    fun getLocalUser(token: String) {
        viewModelScope.launch {
            getLocalUserUseCase.execute(token)
                .collect { user ->
                    _pegawai.value = user.pegawai
                }
        }
    }

    private fun retrieveDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // e.g. January = 01, February = 02, etc.
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return String.format("%04d-%02d-%02d", year, month, day)
    }
}
