package dev.ikti.profile.presentation

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.data.local.entity.LocalUserEntity
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.domain.usecase.file.UploadImageUseCase
import dev.ikti.core.domain.usecase.preference.ClearUserTokenUseCase
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.user.DeleteLocalUserUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserInfoUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserUseCase
import dev.ikti.core.domain.usecase.user.UpdateLocalUserUseCase
import dev.ikti.core.util.NetworkConstant.ERR_FILE_UNSUPPORTED
import dev.ikti.core.util.NetworkConstant.ERR_UNAUTHORIZED
import dev.ikti.core.util.NetworkConstant.ERR_UNKNOWN_ERROR
import dev.ikti.core.util.NetworkException
import dev.ikti.core.util.UIState
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.domain.usecase.UpdateProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val application: Application,
    private val clearUserTokenUseCase: ClearUserTokenUseCase,
    private val deleteLocalUserUseCase: DeleteLocalUserUseCase,
    private val geocoder: Geocoder,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getLocalUserInfoUseCase: GetLocalUserInfoUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val updateLocalUserUseCase: UpdateLocalUserUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {
    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _stateProfile: MutableStateFlow<UIState<UserInfo>> = MutableStateFlow(UIState.Empty)
    val stateProfile: StateFlow<UIState<UserInfo>> = _stateProfile

    private val _stateLogout: MutableStateFlow<UIState<Unit>> = MutableStateFlow(UIState.Empty)
    val stateLogout: StateFlow<UIState<Unit>> = _stateLogout

    private val _stateEdit: MutableStateFlow<UIState<Unit>> =
        MutableStateFlow(UIState.Empty)
    val stateEdit: StateFlow<UIState<Unit>> = _stateEdit

    private val _stateUpload: MutableStateFlow<UIState<String>> =
        MutableStateFlow(UIState.Empty)
    val stateUpload: StateFlow<UIState<String>> = _stateUpload

    private val _stateLocation: MutableStateFlow<UIState<Address>> =
        MutableStateFlow(UIState.Empty)
    val stateLocation: StateFlow<UIState<Address>> = _stateLocation

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
                val response = getLocalUserInfoUseCase.execute(token)
                response.collect { user ->
                    _stateProfile.value = UIState.Success(user)
                }
            } catch (_: Exception) {
                _stateProfile.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun userLogout(token: String) {
        _stateLogout.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = getLocalUserUseCase.execute(token)
                response.collect { data ->
                    deleteLocalUserUseCase.execute(data)
                    clearUserTokenUseCase.execute(Unit)
                }

                _stateLogout.value = UIState.Success(Unit)
            } catch (_: Exception) {
                _stateLogout.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun userUpdate(token: String, profile: ProfileRequest) {
        _stateEdit.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = updateProfileUseCase.execute(token, profile.akun, profile)
                response.collect { data ->
                    try {
                        val localUser = getLocalUserUseCase.execute(token)
                        localUser.collect { local ->
                            val user = LocalUserEntity(
                                token,
                                data.data.akun,
                                local.pegawai,
                                local.nama,
                                local.nip,
                                local.role,
                                data.data.email,
                                local.telepon,
                                data.data.foto,
                                data.data.alamat,
                                data.data.alamatLat,
                                data.data.alamatLon,
                                local.fotoPegawai
                            )

                            updateLocalUserUseCase.execute(user)
                            _stateEdit.value = UIState.Success(Unit)
                        }
                    } catch (_: Exception) {
                        _stateEdit.value = UIState.Error(ERR_UNKNOWN_ERROR)
                    }
                }
            } catch (_: Exception) {
                _stateEdit.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun uploadImage(token: String, uri: Uri) {
        _stateUpload.value = UIState.Loading
        viewModelScope.launch {
            try {
                val inputStream = application.contentResolver.openInputStream(uri)
                val file = File.createTempFile("tmp", ".jpg")
                val outputStream = file.outputStream()
                val buffer = ByteArray(1024)
                var read: Int
                while (inputStream!!.read(buffer).also { read = it } != -1) {
                    outputStream.write(buffer, 0, read)
                }
                outputStream.flush()
                inputStream.close()
                outputStream.close()

                val image = file.asRequestBody("image/jpeg".toMediaType())
                val multipart: MultipartBody.Part =
                    MultipartBody.Part.createFormData("file", file.name, image)

                val response = uploadImageUseCase.execute(token, multipart)
                response.collect { data ->
                    _stateUpload.value = UIState.Success(data.data.url)
                }
            } catch (e: Exception) {
                when (e) {
                    NetworkException.FileUnsupportedException -> _stateUpload.value = UIState.Error(
                        ERR_FILE_UNSUPPORTED
                    )

                    NetworkException.UnauthorizedException -> _stateUpload.value = UIState.Error(
                        ERR_UNAUTHORIZED
                    )

                    else -> _stateUpload.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    fun getMarkerAddress(lat: Double, lon: Double) {
        _stateLocation.value = UIState.Loading
        try {
            if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(
                    lat,
                    lon,
                    1
                ) { addresses ->
                    _stateLocation.value = UIState.Success(addresses[0])
                }
            } else {
                val addresses = geocoder.getFromLocation(
                    lat,
                    lon,
                    1
                )

                _stateLocation.value = if (!addresses.isNullOrEmpty()) {
                    UIState.Success(addresses[0])
                } else {
                    UIState.Error("NullAddress")
                }
            }
        } catch (_: Exception) {
            _stateLocation.value = UIState.Error(ERR_UNKNOWN_ERROR)
        }
    }
}
