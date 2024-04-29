package dev.ikti.profile.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserInfoUseCase
import dev.ikti.core.util.UIState
import dev.ikti.profile.util.ProfileConstant.ERR_ACCOUNT_NOT_FOUND
import dev.ikti.profile.util.ProfileConstant.ERR_UNKNOWN_ERROR
import dev.ikti.profile.util.ProfileException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getLocalUserInfoUseCase: GetLocalUserInfoUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
) : ViewModel() {
    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _stateProfile: MutableStateFlow<UIState<Unit>> = MutableStateFlow(UIState.Empty)
    val stateProfile: StateFlow<UIState<Unit>> = _stateProfile

    private val _userInfo =
        mutableStateOf(
            UserInfo(
                "",
                "",
                "",
                "",
                "",
                "",
                Float.NaN,
                Float.NaN
            )
        )
    val userInfo: State<UserInfo> get() = _userInfo

    fun getUserToken(state: Unit) {
        viewModelScope.launch {
            getUserTokenUseCase.execute(state)
                .collect { token ->
                    _token.value = token
                }
        }
    }

    fun getUserInfo(token: String) {
        _stateProfile.value = UIState.Loading

        viewModelScope.launch {
            try {
                delay(500L)
                val response = getLocalUserInfoUseCase.execute(token)
                response.collect { user ->
                    _userInfo.value = UserInfo(
                        user.akun,
                        user.nama,
                        user.email,
                        user.role,
                        user.foto,
                        user.alamat,
                        user.alamatLat,
                        user.alamatLon
                    )
                }

                _stateProfile.value = UIState.Success(Unit)
            } catch (e: Exception) {
                when (e) {
                    ProfileException.AccountNotFoundException -> _stateProfile.value =
                        UIState.Error(
                            ERR_ACCOUNT_NOT_FOUND
                        )

                    else -> _stateProfile.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }
}