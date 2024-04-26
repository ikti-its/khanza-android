package dev.ikti.khanza.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.usecase.user.InsertLocalUserUseCase
import dev.ikti.core.util.UIState
import dev.ikti.khanza.data.model.HomeResponse
import dev.ikti.khanza.domain.usecase.HomeUseCase
import dev.ikti.khanza.util.HomeConstant
import dev.ikti.khanza.util.HomeConstant.ERR_ACCOUNT_NOT_FOUND
import dev.ikti.khanza.util.HomeConstant.ERR_FAILED_TO_INSERT_USER
import dev.ikti.khanza.util.HomeException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val insertLocalUserUseCase: InsertLocalUserUseCase
) : ViewModel() {
    private val _stateHome: MutableStateFlow<UIState<Unit>> = MutableStateFlow(UIState.Empty)
    val stateHome: StateFlow<UIState<Unit>> = _stateHome

    private val _userHome =
        mutableStateOf(
            HomeResponse(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                0.0f,
                0.0f,
                "",
                "",
                "",
                "",
                false
            )
        )
    val userHome: State<HomeResponse> get() = _userHome

    fun getUserHome(token: String) {
        _stateHome.value = UIState.Loading
        val today = retrieveDate()

        viewModelScope.launch {
            try {
                val response = homeUseCase.execute(token, today)
                response.collect { home ->
                    _userHome.value = HomeResponse(
                        home.data.akun,
                        home.data.pegawai,
                        home.data.nama,
                        home.data.nip,
                        home.data.email,
                        home.data.telepon,
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

                try {
                    val user = LocalUserEntity(
                        _userHome.value.akun,
                        _userHome.value.pegawai,
                        _userHome.value.nama,
                        _userHome.value.nip,
                        _userHome.value.email,
                        _userHome.value.telepon,
                        _userHome.value.profil,
                        _userHome.value.alamat,
                        _userHome.value.alamatLat,
                        _userHome.value.alamatLon,
                        _userHome.value.foto,
                    )

                    insertLocalUserUseCase.execute(user)
                } catch (_: Exception) {
                    _stateHome.value = UIState.Error(ERR_FAILED_TO_INSERT_USER)
                }

                _stateHome.value = UIState.Success(Unit)
            } catch (e: Exception) {
                when (e) {
                    HomeException.AccountNotFoundException -> _stateHome.value = UIState.Error(
                        ERR_ACCOUNT_NOT_FOUND
                    )

                    else -> _stateHome.value = UIState.Error(HomeConstant.ERR_UNKNOWN_ERROR)
                }
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