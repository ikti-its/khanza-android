package dev.ikti.kehadiran.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.util.UIState
import dev.ikti.kehadiran.data.model.PengajuanRequest
import dev.ikti.kehadiran.data.model.PengajuanResponse
import dev.ikti.kehadiran.domain.usecase.PengajuanCreateUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetAllUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetByIdUseCase
import dev.ikti.kehadiran.domain.usecase.PengajuanGetByPegawaiIdUseCase
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
    private val pengajuanGetByIdUseCase: PengajuanGetByIdUseCase,
    private val pengajuanGetByPegawaiIdUseCase: PengajuanGetByPegawaiIdUseCase,
    private val pengajuanUpdateUseCase: PengajuanUpdateUseCase
) : ViewModel() {
    private val _statePengajuan: MutableStateFlow<UIState<PengajuanResponse>> =
        MutableStateFlow(UIState.Empty)
    val statePengajuan: StateFlow<UIState<PengajuanResponse>> = _statePengajuan

    private val _stateStatus: MutableStateFlow<UIState<List<PengajuanResponse>>> =
        MutableStateFlow(UIState.Empty)
    val stateStatus: StateFlow<UIState<List<PengajuanResponse>>> = _stateStatus

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
}
