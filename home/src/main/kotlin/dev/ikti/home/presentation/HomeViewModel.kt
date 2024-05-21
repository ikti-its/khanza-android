package dev.ikti.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.usecase.preference.ClearUserTokenUseCase
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.user.DeleteLocalUserUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserUseCase
import dev.ikti.core.domain.usecase.user.InsertLocalUserUseCase
import dev.ikti.core.util.UIState
import dev.ikti.home.data.model.HomeResponse
import dev.ikti.home.domain.usecase.HomeUseCase
import dev.ikti.home.util.HomeConstant.ERR_ACCOUNT_NOT_FOUND
import dev.ikti.home.util.HomeConstant.ERR_ACCOUNT_UNAUTHORIZED
import dev.ikti.home.util.HomeConstant.ERR_FAILED_TO_INSERT_USER
import dev.ikti.home.util.HomeConstant.ERR_UNKNOWN_ERROR
import dev.ikti.home.util.HomeException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val homeUseCase: HomeUseCase,
    private val insertLocalUserUseCase: InsertLocalUserUseCase,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val deleteLocalUserUseCase: DeleteLocalUserUseCase,
    private val clearUserTokenUseCase: ClearUserTokenUseCase
) : ViewModel() {
    private val _userToken = MutableStateFlow("")
    val userToken: StateFlow<String> = _userToken

    private val _stateHome: MutableStateFlow<UIState<Unit>> = MutableStateFlow(UIState.Empty)
    val stateHome: StateFlow<UIState<Unit>> = _stateHome

    private val _stateLogout: MutableStateFlow<UIState<Unit>> = MutableStateFlow(UIState.Empty)
    val stateLogout: StateFlow<UIState<Unit>> = _stateLogout

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
                "",
                -7.2821,
                112.7949,
                "",
                "",
                "",
                "",
                false
            )
        )
    val userHome: State<HomeResponse> get() = _userHome

    fun getUserToken(state: Unit) {
        viewModelScope.launch {
            getUserTokenUseCase.execute(state)
                .collect { token ->
                    _userToken.value = token
                }
        }
    }

    fun getUserHome(token: String) {
        _stateHome.value = UIState.Loading
        val today = retrieveDate()

        viewModelScope.launch {
            try {
                delay(500L)
                val response = homeUseCase.execute(token, today)
                response.collect { home ->
                    _userHome.value = HomeResponse(
                        home.data.akun,
                        home.data.pegawai,
                        home.data.nama,
                        home.data.nip,
                        home.data.role,
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
                        token,
                        _userHome.value.akun,
                        _userHome.value.pegawai,
                        _userHome.value.nama,
                        _userHome.value.nip,
                        _userHome.value.role,
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
                    HomeException.AccountUnauthorizedException -> _stateHome.value = UIState.Error(
                        ERR_ACCOUNT_UNAUTHORIZED
                    )

                    HomeException.AccountNotFoundException -> _stateHome.value = UIState.Error(
                        ERR_ACCOUNT_NOT_FOUND
                    )

                    else -> _stateHome.value = UIState.Error(ERR_UNKNOWN_ERROR)
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

    fun userLogout(token: String) {
        _stateLogout.value = UIState.Loading

        viewModelScope.launch {
            try {
                val response = getLocalUserUseCase.execute(token)
                response.collect { data ->
                    deleteLocalUserUseCase.execute(data)
                    clearUserTokenUseCase.execute(Unit)
                    delay(500L)
                }

                _stateLogout.value = UIState.Success(Unit)
            } catch (e: Exception) {
                _stateLogout.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }
}