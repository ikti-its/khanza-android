package dev.ikti.kehadiran.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.domain.usecase.user.GetLocalUserUseCase
import dev.ikti.core.util.NetworkConstant.ERR_NOT_FOUND
import dev.ikti.core.util.NetworkConstant.ERR_UNKNOWN_ERROR
import dev.ikti.core.util.NetworkException
import dev.ikti.core.util.UIState
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.PresensiResponse
import dev.ikti.kehadiran.domain.usecase.KehadiranGetJadwalByPegawaiIdUseCase
import dev.ikti.kehadiran.domain.usecase.KehadiranGetPresensiByPegawaiIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class KehadiranViewModel @Inject constructor(
    private val getJadwalByPegawaiIdUseCase: KehadiranGetJadwalByPegawaiIdUseCase,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getPresensiByPegawaiIdUseCase: KehadiranGetPresensiByPegawaiIdUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase
) : ViewModel() {
    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _pegawai = MutableStateFlow("")
    val pegawai: StateFlow<String> = _pegawai

    private val _stateJadwal: MutableStateFlow<UIState<List<JadwalResponse>>> =
        MutableStateFlow(UIState.Empty)
    val stateJadwal: StateFlow<UIState<List<JadwalResponse>>> = _stateJadwal

    private val _stateRiwayat: MutableStateFlow<UIState<List<PresensiResponse>>> =
        MutableStateFlow(UIState.Empty)
    val stateRiwayat: StateFlow<UIState<List<PresensiResponse>>> = _stateRiwayat

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

    fun getJadwal(token: String, id: String) {
        _stateJadwal.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = getJadwalByPegawaiIdUseCase.execute(token, id)
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

    @SuppressLint("SimpleDateFormat")
    fun getRiwayat(token: String, id: String) {
        _stateRiwayat.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = getPresensiByPegawaiIdUseCase.execute(token, id)
                response.collect { res ->
                    val sortedTanggal = res.data.sortedByDescending {
                        SimpleDateFormat("yyyy-MM-dd").parse(
                            it.tanggal
                        )
                    }

                    val sortedMasuk = sortedTanggal.sortedByDescending {
                        it.masuk
                    }

                    _stateRiwayat.value = UIState.Success(sortedMasuk)
                }
            } catch (e: Exception) {
                when (e) {
                    NetworkException.NotFoundException -> _stateRiwayat.value = UIState.Error(
                        ERR_NOT_FOUND
                    )

                    else -> _stateRiwayat.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }
}
