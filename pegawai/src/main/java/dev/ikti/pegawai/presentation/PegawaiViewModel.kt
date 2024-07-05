package dev.ikti.pegawai.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.core.domain.usecase.preference.GetUserTokenUseCase
import dev.ikti.core.util.NetworkConstant
import dev.ikti.core.util.NetworkException
import dev.ikti.core.util.UIState
import dev.ikti.pegawai.data.model.PegawaiResponse
import dev.ikti.pegawai.domain.model.Ketersediaan
import dev.ikti.pegawai.domain.usecase.GetKetersediaanUseCase
import dev.ikti.pegawai.domain.usecase.GetLokasiUseCase
import dev.ikti.pegawai.domain.usecase.GetPegawaiUseCase
import dev.ikti.pegawai.util.PegawaiConstant.ERR_ACCOUNT_NOT_FOUND
import dev.ikti.pegawai.util.PegawaiConstant.ERR_UNKNOWN_ERROR
import dev.ikti.pegawai.util.PegawaiException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@HiltViewModel
class PegawaiViewModel @Inject constructor(
    private val getKetersediaanUseCase: GetKetersediaanUseCase,
    private val getLokasiUseCase: GetLokasiUseCase,
    private val getPegawaiUseCase: GetPegawaiUseCase,
    private val getUserTokenUseCase: GetUserTokenUseCase
) : ViewModel() {
    private val _token = MutableStateFlow("")
    val token: StateFlow<String> = _token

    private val _stateData: MutableStateFlow<UIState<PegawaiResponse>> =
        MutableStateFlow(UIState.Empty)
    val stateData: StateFlow<UIState<PegawaiResponse>> = _stateData

    private val _stateKetersediaan: MutableStateFlow<UIState<List<Ketersediaan>>> =
        MutableStateFlow(UIState.Empty)
    val stateKetersediaan: StateFlow<UIState<List<Ketersediaan>>> = _stateKetersediaan

    private val _stateLokasi: MutableStateFlow<UIState<LatLng>> =
        MutableStateFlow(UIState.Empty)
    val stateLokasi: StateFlow<UIState<LatLng>> = _stateLokasi

    init {
        getUserToken()
    }

    fun getUserToken() {
        viewModelScope.launch {
            getUserTokenUseCase.execute(Unit)
                .collect { token ->
                    _token.value = token
                }
        }
    }

    fun getPegawai(token: String) {
        _stateData.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = getPegawaiUseCase.execute(token)
                response.collect { pegawai ->
                    _stateData.value = UIState.Success(pegawai.data)
                }
            } catch (e: Exception) {
                when (e) {
                    PegawaiException.AccountNotFoundException -> _stateData.value =
                        UIState.Error(ERR_ACCOUNT_NOT_FOUND)

                    else -> _stateData.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    fun getLokasi(token: String) {
        _stateLokasi.value = UIState.Loading
        viewModelScope.launch {
            try {
                val response = getLokasiUseCase.execute(token)
                response.collect { res ->
                    _stateLokasi.value =
                        UIState.Success(LatLng(res.data.latitude, res.data.longitude))
                }
            } catch (e: Exception) {
                when (e) {
                    NetworkException.NotFoundException -> _stateLokasi.value = UIState.Error(
                        NetworkConstant.ERR_NOT_FOUND
                    )

                    else -> _stateLokasi.value = UIState.Error(NetworkConstant.ERR_UNKNOWN_HOST)
                }
            }
        }
    }

    fun queryKetersediaan(token: String, query: String, latitude: Double, longitude: Double) {
        _stateKetersediaan.value = UIState.Loading
        val today = retrieveDate()

        viewModelScope.launch {
            try {
                val response = getKetersediaanUseCase.execute(token, today)
                response.collect { list ->
                    if (query != "") {
                        val filtered = list.data.filter { ketersediaan ->
                            ketersediaan.nama.contains(query, ignoreCase = true)
                        }
                        val ketersediaanList = filtered.map { ketersediaan ->
                            val distance =
                                calculateDistance(
                                    ketersediaan.latitude,
                                    ketersediaan.longitude,
                                    latitude,
                                    longitude
                                )

                            Ketersediaan(
                                ketersediaan.nama,
                                ketersediaan.foto,
                                ketersediaan.nip,
                                ketersediaan.telepon,
                                ketersediaan.jabatan,
                                ketersediaan.departemen,
                                ketersediaan.alamat,
                                distance,
                                ketersediaan.available,
                                false
                            )
                        }

                        _stateKetersediaan.value =
                            UIState.Success(ketersediaanList.sortedBy { it.distance })
                    } else {
                        val ketersediaanList = list.data.map { ketersediaan ->
                            val distance =
                                calculateDistance(
                                    ketersediaan.latitude,
                                    ketersediaan.longitude,
                                    latitude,
                                    longitude
                                )

                            Ketersediaan(
                                ketersediaan.nama,
                                ketersediaan.foto,
                                ketersediaan.nip,
                                ketersediaan.telepon,
                                ketersediaan.jabatan,
                                ketersediaan.departemen,
                                ketersediaan.alamat,
                                distance,
                                ketersediaan.available,
                                false
                            )
                        }

                        _stateKetersediaan.value =
                            UIState.Success(ketersediaanList.sortedBy { it.distance })
                    }
                }
            } catch (e: Exception) {
                when (e) {
                    PegawaiException.AccountNotFoundException -> _stateKetersediaan.value =
                        UIState.Error(ERR_ACCOUNT_NOT_FOUND)

                    else -> _stateKetersediaan.value = UIState.Error(ERR_UNKNOWN_ERROR)
                }
            }
        }
    }

    private fun calculateDistance(
        latitude: Double,
        longitude: Double,
        originLatitude: Double,
        originLongitude: Double
    ): Double {
        val earthRadius = 6371.0

        val latitudeRadius = Math.toRadians(latitude)
        val longitudeRadius = Math.toRadians(longitude)
        val locationLatitudeRadius = Math.toRadians(originLatitude)
        val locationLongitudeRadius = Math.toRadians(originLongitude)

        val dLat = locationLatitudeRadius - latitudeRadius
        val dLon = locationLongitudeRadius - longitudeRadius

        val a = sin(dLat / 2) * sin(dLat / 2) + cos(latitudeRadius) * cos(locationLatitudeRadius) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * asin(sqrt(a))

        return earthRadius * c
    }

    private fun retrieveDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // e.g. January = 01, February = 02, etc.
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return String.format("%04d-%02d-%02d", year, month, day)
    }
}
