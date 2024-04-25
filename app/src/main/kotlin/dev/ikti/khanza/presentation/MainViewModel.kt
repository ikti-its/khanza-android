package dev.ikti.khanza.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.util.UIState
import dev.ikti.khanza.data.model.HomeResponse
import dev.ikti.khanza.domain.usecase.HomeUseCase
import dev.ikti.khanza.util.HomeConstant.ERR_UNKNOWN_ERROR
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    private val _stateHome: MutableStateFlow<UIState<Unit>> = MutableStateFlow(UIState.Empty)
    val stateHome: StateFlow<UIState<Unit>> = _stateHome

    private val _userHome =
        mutableStateOf(HomeResponse("", "", "", "", "", "", 0.0f, 0.0f, "", "", "", "", false))
    val userHome: State<HomeResponse> get() = _userHome

    data class DateInfo(val day: Int, val date: String)

    fun getUserHome(token: String) {
        _stateHome.value = UIState.Loading
        val today = retrieveDate()

        viewModelScope.launch {
            try {
                val response = homeUseCase.execute(token, today.day, today.date)
                response.collect { home ->
                    _userHome.value = HomeResponse(
                        home.data.akun,
                        home.data.pegawai,
                        home.data.nama,
                        home.data.nip,
                        home.data.profil,
                        home.data.alamat,
                        home.data.alamatLat,
                        home.data.alamatLon,
                        home.data.foto,
                        home.data.shift,
                        home.data.jamMasuk,
                        home.data.jamPulang,
                        home.data.status
                    )
                }

                _stateHome.value = UIState.Success(Unit)
            } catch (_: Exception) {
                // TODO: Verbose error handling
                _stateHome.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    private fun retrieveDate(): DateInfo {
        val calendar = Calendar.getInstance()
        val dayOfWeek =
            calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY // e.g. Monday = 1, Tuesday = 2, etc.
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // e.g. January = 01, February = 02, etc.
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val date = String.format("%04d-%02d-%02d", year, month, day)

        return DateInfo(dayOfWeek, date)
    }
}