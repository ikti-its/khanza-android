package dev.ikti.kehadiran.presentation

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.usecase.file.UploadImageUseCase
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserUseCase
import dev.ikti.core.util.NetworkConstant
import dev.ikti.core.util.NetworkConstant.ERR_NOT_FOUND
import dev.ikti.core.util.NetworkConstant.ERR_UNKNOWN_ERROR
import dev.ikti.core.util.NetworkException
import dev.ikti.core.util.UIState
import dev.ikti.kehadiran.data.model.AttendRequest
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.LeaveRequest
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.data.model.StatusPresensiResponse
import dev.ikti.kehadiran.domain.usecase.PresensiAttendUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetJadwalUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetPresensiUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiLeaveUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PresensiViewModel @Inject constructor(
    private val application: Application,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val attendUseCase: PresensiAttendUseCase,
    private val getJadwalUseCase: PresensiGetJadwalUseCase,
    private val getPresensiUseCase: PresensiGetPresensiUseCase,
    private val leaveUseCase: PresensiLeaveUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {
    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _pegawai = MutableStateFlow("")
    val pegawai: StateFlow<String> = _pegawai

    private val _stateJadwal: MutableStateFlow<UIState<JadwalResponse>> =
        MutableStateFlow(UIState.Empty)
    val stateJadwal: StateFlow<UIState<JadwalResponse>> = _stateJadwal

    private val _stateStatus: MutableStateFlow<UIState<StatusPresensiResponse>> =
        MutableStateFlow(UIState.Empty)
    val stateStatus: StateFlow<UIState<StatusPresensiResponse>> = _stateStatus

    private val _stateAttend: MutableStateFlow<UIState<PresensiResponse>> =
        MutableStateFlow(UIState.Empty)
    val stateAttend: StateFlow<UIState<PresensiResponse>> = _stateAttend

    private val _stateLeave: MutableStateFlow<UIState<PresensiResponse>> =
        MutableStateFlow(UIState.Empty)
    val stateLeave: StateFlow<UIState<PresensiResponse>> = _stateLeave

    private val _stateUpload: MutableStateFlow<UIState<String>> =
        MutableStateFlow(UIState.Empty)
    val stateUpload: StateFlow<UIState<String>> = _stateUpload

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

        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day)
    }

    fun getJadwal(token: String, id: String) {
        _stateJadwal.value = UIState.Loading
        viewModelScope.launch {
            try {
                val hari = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                    Calendar.MONDAY -> 1
                    Calendar.TUESDAY -> 2
                    Calendar.WEDNESDAY -> 3
                    Calendar.THURSDAY -> 4
                    Calendar.FRIDAY -> 5
                    Calendar.SATURDAY -> 6
                    Calendar.SUNDAY -> 7
                    else -> 0
                }
                val response = getJadwalUseCase.execute(token, id, hari)
                response.collect { res ->
                    _stateJadwal.value = UIState.Success(res.data)
                }
            } catch (e: Exception) {
                when (e) {
                    NetworkException.NotFoundException -> _stateJadwal.value =
                        UIState.Error(ERR_NOT_FOUND)

                    else -> _stateJadwal.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    fun getStatus(token: String, id: String) {
        _stateStatus.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = getPresensiUseCase.execute(token, id)
                response.collect { res ->
                    _stateStatus.value = UIState.Success(res.data)
                }
            } catch (e: Exception) {
                when (e) {
                    NetworkException.NotFoundException -> _stateStatus.value = UIState.Error(
                        ERR_NOT_FOUND
                    )

                    else -> _stateStatus.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    fun attend(token: String, pegawai: String, jadwal: String, foto: String) {
        _stateAttend.value = UIState.Loading
        viewModelScope.launch {
            try {
                val attend = AttendRequest(
                    pegawai,
                    jadwal,
                    retrieveDate(),
                    foto
                )
                val response = attendUseCase.execute(token, attend)
                response.collect { res ->
                    _stateAttend.value = UIState.Success(res.data)
                }
            } catch (e: Exception) {
                when (e) {
                    NetworkException.NotFoundException -> _stateAttend.value = UIState.Error(
                        ERR_NOT_FOUND
                    )

                    else -> _stateAttend.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    fun leave(token: String, id: String, pegawai: String, emergency: Boolean) {
        _stateLeave.value = UIState.Loading
        viewModelScope.launch {
            try {
                val leave = LeaveRequest(
                    id,
                    pegawai
                )
                val response = leaveUseCase.execute(token, emergency, leave)
                response.collect { res ->
                    _stateLeave.value = UIState.Success(res.data)
                }
            } catch (e: Exception) {
                when (e) {
                    NetworkException.NotFoundException -> _stateLeave.value = UIState.Error(
                        ERR_NOT_FOUND
                    )

                    else -> _stateLeave.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    fun uploadSwafoto(
        token: String,
        cameraController: CameraController
    ) {
        cameraController.takePicture(
            ContextCompat.getMainExecutor(application),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    _stateUpload.value = UIState.Loading
                    viewModelScope.launch {
                        try {
                            val file = File.createTempFile("tmp", ".jpg")
                            val outputStream = file.outputStream()

                            val compressed = compressImage(image)

                            outputStream.write(compressed)
                            outputStream.flush()
                            outputStream.close()

                            val req = file.asRequestBody("image/jpeg".toMediaType())
                            val multipart: MultipartBody.Part =
                                MultipartBody.Part.createFormData("file", file.name, req)

                            val response = uploadImageUseCase.execute(token, multipart)
                            response.collect { data ->
                                _stateUpload.value = UIState.Success(data.data.url)
                            }
                        } catch (e: Exception) {
                            when (e) {
                                NetworkException.FileUnsupportedException -> _stateUpload.value =
                                    UIState.Error(
                                        NetworkConstant.ERR_FILE_UNSUPPORTED
                                    )

                                NetworkException.UnauthorizedException -> _stateUpload.value =
                                    UIState.Error(
                                        NetworkConstant.ERR_UNAUTHORIZED
                                    )

                                else -> _stateUpload.value = UIState.Error(ERR_UNKNOWN_ERROR)
                            }
                        }
                    }
                }
            }
        )
    }

    private fun compressImage(image: ImageProxy): ByteArray {
        val buffer = image.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)

        val MAX_SIZE = 1024 * 1024
        var scale = 1
        while (options.outWidth / scale / 2 >= MAX_SIZE &&
            options.outHeight / scale / 2 >= MAX_SIZE
        ) {
            scale *= 2
        }

        options.inJustDecodeBounds = false
        options.inSampleSize = scale

        val compressedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
        val outputStream = ByteArrayOutputStream()
        compressedBitmap.compress(
            Bitmap.CompressFormat.JPEG,
            40,
            outputStream
        )
        return outputStream.toByteArray()
    }
}
