package dev.ikti.core.util

import com.google.android.gms.maps.model.LatLng

sealed class LocationState {
    data object NoPermission : LocationState()
    data object Disabled : LocationState()
    data object Loading : LocationState()
    data class Success(val location: LatLng) : LocationState()
    data object Error : LocationState()
}