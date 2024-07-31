package dev.ikti.kehadiran.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.util.UIState
import dev.ikti.kehadiran.data.model.JadwalResponse
import dev.ikti.kehadiran.data.model.TukarRequest
import dev.ikti.kehadiran.data.model.TukarResponse
import dev.ikti.kehadiran.domain.model.DaftarJadwal
import dev.ikti.kehadiran.domain.model.DaftarTukar
import dev.ikti.kehadiran.domain.usecase.PengajuanGetPegawaiUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetJadwalUseCase
import dev.ikti.kehadiran.domain.usecase.PresensiGetUseCase
import dev.ikti.kehadiran.domain.usecase.TukarCreateUseCase
import dev.ikti.kehadiran.domain.usecase.TukarGetRecipientUseCase
import dev.ikti.kehadiran.domain.usecase.TukarGetSenderUseCase
import dev.ikti.kehadiran.domain.usecase.TukarUpdateUseCase
import dev.ikti.kehadiran.util.KehadiranConstant.ERR_UNKNOWN_ERROR
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TukarViewModel @Inject constructor(
    private val presensiGetJadwalUseCase: PresensiGetJadwalUseCase,
    private val presensiGetUseCase: PresensiGetUseCase,
    private val pengajuanGetPegawaiUseCase: PengajuanGetPegawaiUseCase,
    private val tukarCreateUseCase: TukarCreateUseCase,
    private val tukarGetSenderUseCase: TukarGetSenderUseCase,
    private val tukarGetRecipientUseCase: TukarGetRecipientUseCase,
    private val tukarUpdateUseCase: TukarUpdateUseCase
) : ViewModel() {
    private val _stateTukar: MutableStateFlow<UIState<TukarResponse>> =
        MutableStateFlow(UIState.Empty)
    val stateTukar: StateFlow<UIState<TukarResponse>> = _stateTukar

    private val _stateTukarJadwal: MutableStateFlow<UIState<JadwalResponse>> =
        MutableStateFlow(UIState.Empty)
    val stateTukarJadwal: StateFlow<UIState<JadwalResponse>> = _stateTukarJadwal

    private val _stateTukarPegawai: MutableStateFlow<UIState<List<DaftarJadwal>>> =
        MutableStateFlow(UIState.Empty)
    val stateTukarPegawai: StateFlow<UIState<List<DaftarJadwal>>> = _stateTukarPegawai

    private val _stateStatusTukar: MutableStateFlow<UIState<List<DaftarTukar>>> =
        MutableStateFlow(UIState.Empty)
    val stateStatusTukar: StateFlow<UIState<List<DaftarTukar>>> = _stateStatusTukar

    private val _stateTinjauTukar: MutableStateFlow<UIState<List<DaftarTukar>>> =
        MutableStateFlow(UIState.Empty)
    val stateTinjauTukar: StateFlow<UIState<List<DaftarTukar>>> = _stateTinjauTukar

    private val _stateUpdateTukar: MutableStateFlow<UIState<Unit>> =
        MutableStateFlow(UIState.Empty)
    val stateUpdateTukar: StateFlow<UIState<Unit>> = _stateUpdateTukar

    fun getPegawai(token: String, hari: Int) {
        _stateTukarPegawai.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = presensiGetUseCase.execute(token, hari)
                response.collect { jadwal ->
                    val daftarJadwal = jadwal.data.map {
                        val pegawaiResponse =
                            pengajuanGetPegawaiUseCase.execute(token, it.pegawai)
                        var nama = ""
                        pegawaiResponse.collect { pegawaiRes ->
                            nama = pegawaiRes.data.nama
                        }

                        DaftarJadwal(
                            it.id,
                            it.pegawai,
                            nama,
                            it.shift,
                            it.masuk,
                            it.pulang
                        )
                    }

                    _stateTukarPegawai.value = UIState.Success(daftarJadwal)
                }
            } catch (_: Exception) {
                _stateTukarPegawai.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun getJadwal(token: String, pegawai: String, hari: Int) {
        _stateTukarJadwal.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = presensiGetJadwalUseCase.execute(token, pegawai, hari)
                response.collect { jadwal ->
                    _stateTukarJadwal.value = UIState.Success(jadwal.data)
                }
            } catch (_: Exception) {
                _stateTukarJadwal.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun createTukar(token: String, tukar: TukarRequest) {
        _stateTukar.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = tukarCreateUseCase.execute(token, tukar)
                response.collect { tukar ->
                    _stateTukar.value = UIState.Success(tukar.data)
                }
            } catch (_: Exception) {
                _stateTukar.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun getSender(token: String, pegawai: String) {
        _stateStatusTukar.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = tukarGetSenderUseCase.execute(token, pegawai)
                response.collect { tukar ->
                    val daftarTukar = tukar.data.map {
                        val pegawaiResponse =
                            pengajuanGetPegawaiUseCase.execute(token, it.recipient)
                        var nama = ""
                        pegawaiResponse.collect { pegawaiRes ->
                            nama = pegawaiRes.data.nama
                        }

                        DaftarTukar(
                            it.id,
                            nama,
                            it.sender,
                            it.recipient,
                            it.hari,
                            it.senderShift,
                            it.recipientShift,
                            it.status
                        )
                    }

                    _stateStatusTukar.value = UIState.Success(daftarTukar)
                }
            } catch (_: Exception) {
                _stateStatusTukar.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun getRecipient(token: String, pegawai: String) {
        _stateTinjauTukar.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = tukarGetRecipientUseCase.execute(token, pegawai)
                response.collect { tukar ->
                    val filtered = tukar.data.filter { ajuan ->
                        ajuan.status == "Menunggu"
                    }

                    val daftarTukar = filtered.map {
                        val pegawaiResponse =
                            pengajuanGetPegawaiUseCase.execute(token, it.sender)
                        var nama = ""
                        pegawaiResponse.collect { pegawaiRes ->
                            nama = pegawaiRes.data.nama
                        }

                        DaftarTukar(
                            it.id,
                            nama,
                            it.sender,
                            it.recipient,
                            it.hari,
                            it.senderShift,
                            it.recipientShift,
                            it.status
                        )
                    }

                    _stateTinjauTukar.value = UIState.Success(daftarTukar)
                }
            } catch (_: Exception) {
                _stateTinjauTukar.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun updateStatus(token: String, id: String, request: TukarRequest) {
        _stateUpdateTukar.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = tukarUpdateUseCase.execute(token, id, request)
                response.collect { ajuan ->
                    if (ajuan.status != "Diproses") {
                        _stateUpdateTukar.value = UIState.Success(Unit)
                    } else {
                        _stateUpdateTukar.value = UIState.Error(ERR_UNKNOWN_ERROR)
                    }
                }
            } catch (_: Exception) {
                _stateUpdateTukar.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }
}
