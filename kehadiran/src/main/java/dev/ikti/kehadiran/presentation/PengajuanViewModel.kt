package dev.ikti.kehadiran.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.util.UIState
import dev.ikti.kehadiran.data.model.PengajuanRequest
import dev.ikti.kehadiran.data.model.PengajuanResponse
import dev.ikti.kehadiran.domain.model.PeninjauanPerizinan
import dev.ikti.kehadiran.domain.usecase.PengajuanCreateUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetAllUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetByPegawaiIdUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetPegawaiUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanUpdateUseCase
import dev.ikti.kehadiran.util.KehadiranConstant.ERR_UNKNOWN_ERROR
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PengajuanViewModel @Inject constructor(
    private val pengajuanCreateUseCase: PengajuanCreateUseCase,
    private val pengajuanGetAllUseCase: PengajuanGetAllUseCase,
    private val pengajuanGetByPegawaiIdUseCase: PengajuanGetByPegawaiIdUseCase,
    private val pengajuanGetPegawaiUseCase: PengajuanGetPegawaiUseCase,
    private val pengajuanUpdateUseCase: PengajuanUpdateUseCase
) : ViewModel() {
    private val _statePengajuan: MutableStateFlow<UIState<PengajuanResponse>> =
        MutableStateFlow(UIState.Empty)
    val statePengajuan: StateFlow<UIState<PengajuanResponse>> = _statePengajuan

    private val _stateStatus: MutableStateFlow<UIState<List<PengajuanResponse>>> =
        MutableStateFlow(UIState.Empty)
    val stateStatus: StateFlow<UIState<List<PengajuanResponse>>> = _stateStatus

    private val _statePeninjauanList: MutableStateFlow<UIState<List<PeninjauanPerizinan>>> =
        MutableStateFlow(UIState.Empty)
    val statePeninjauanList: StateFlow<UIState<List<PeninjauanPerizinan>>> = _statePeninjauanList

    private val _stateUpdate: MutableStateFlow<UIState<Unit>> = MutableStateFlow(UIState.Empty)
    val stateUpdate: StateFlow<UIState<Unit>> = _stateUpdate

    fun createPengajuan(token: String, ajuan: PengajuanRequest) {
        _statePengajuan.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = pengajuanCreateUseCase.execute(token, ajuan)
                response.collect { pengajuan ->
                    _statePengajuan.value = UIState.Success(pengajuan.data)
                }
            } catch (_: Exception) {
                _statePengajuan.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun getByPegawaiId(token: String, id: String) {
        _stateStatus.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = pengajuanGetByPegawaiIdUseCase.execute(token, id)
                response.collect { pengajuanList ->
                    _stateStatus.value = UIState.Success(pengajuanList.data)
                }
            } catch (_: Exception) {
                _stateStatus.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun getAll(token: String) {
        _statePeninjauanList.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = pengajuanGetAllUseCase.execute(token)
                response.collect { res ->
                    val filtered = res.data.filter { pengajuan ->
                        pengajuan.status == "Diproses"
                    }

                    val pengajuanList = filtered.map { pengajuan ->
                        val pegawaiResponse =
                            pengajuanGetPegawaiUseCase.execute(token, pengajuan.pegawai)
                        var nama = ""
                        pegawaiResponse.collect { pegawaiRes ->
                            nama = pegawaiRes.data.nama
                        }

                        PeninjauanPerizinan(
                            pengajuan.id,
                            pengajuan.pegawai,
                            nama,
                            pengajuan.mulai,
                            pengajuan.selesai,
                            pengajuan.alasan,
                            pengajuan.status
                        )
                    }

                    _statePeninjauanList.value = UIState.Success(pengajuanList)
                }
            } catch (_: Exception) {
                _statePeninjauanList.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }

    fun updateStatus(token: String, id: String, request: PengajuanRequest) {
        _stateUpdate.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = pengajuanUpdateUseCase.execute(token, id, request)
                response.collect { ajuan ->
                    if (ajuan.status != "Diproses") {
                        _stateUpdate.value = UIState.Success(Unit)
                    } else {
                        _stateUpdate.value = UIState.Error(ERR_UNKNOWN_ERROR)
                    }
                }
            } catch (_: Exception) {
                _stateUpdate.value = UIState.Error(ERR_UNKNOWN_ERROR)
            }
        }
    }
}
