package dev.ikti.profile.presentation.component.organism

import android.Manifest
import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.profile.data.model.ProfileRequest
import dev.ikti.profile.presentation.component.atom.ProfileDetailMapLabel
import dev.ikti.profile.presentation.component.molecule.ProfileEditField
import dev.ikti.profile.presentation.component.molecule.ProfileEditMap
import dev.ikti.profile.presentation.component.molecule.ProfileEditSubmitButton
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_ALAMAT
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_EMAIL
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_PASSWORD
import dev.ikti.profile.util.ProfileConstant.FIELD_TYPE_ROLE

@OptIn(ExperimentalPermissionsApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ProfileEdit(
    modifier: Modifier = Modifier,
    akun: String,
    email: String,
    role: String,
    alamat: String,
    alamatLat: Double,
    alamatLon: Double,
    onSave: (ProfileRequest) -> Unit = {}
) {
    Log.d("EDIT", "LAT $alamatLat , LON $alamatLon")
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    var newEmail by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var newAlamat by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var newAlamatLocation by remember { mutableStateOf(LatLng(alamatLat, alamatLon)) }

    val scrollState = rememberScrollState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newAlamatLocation, 15f)
    }
    var columnScrollingEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving) {
            columnScrollingEnabled = true
        }
    }

    LaunchedEffect(Unit) {
        if (!locationPermissionState.allPermissionsGranted) {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState, columnScrollingEnabled)
            .padding(bottom = 120.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ubah foto profil",
                modifier = modifier.clickable { },
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontFamily = FontGilroy
                )
            )
        }
        Spacer(modifier.size(20.dp))
        ProfileEditField(field = FIELD_TYPE_EMAIL, placeholder = email) { value, error ->
            newEmail = value
            isEmailError = error
        }
        Spacer(modifier.size(10.dp))
        ProfileEditField(field = FIELD_TYPE_PASSWORD) { value, error ->
            newPassword = value
            isPasswordError = error
        }
        Spacer(modifier.size(10.dp))
        ProfileEditField(field = FIELD_TYPE_ROLE, placeholder = role) { _, _ -> }
        Spacer(modifier.size(10.dp))
        ProfileEditField(field = FIELD_TYPE_ALAMAT, placeholder = alamat) { value, _ ->
            newAlamat = value
        }
        Spacer(modifier.size(20.dp))
        ProfileDetailMapLabel()
        Spacer(modifier.size(10.dp))
        ProfileEditMap(
            modifier = Modifier.pointerInteropFilter(
                onTouchEvent = {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            columnScrollingEnabled = false
                            false
                        }

                        else -> {
                            true
                        }
                    }
                }
            ),
            location = newAlamatLocation,
            cameraPositionState = cameraPositionState
        )
        Spacer(modifier.size(30.dp))
        ProfileEditSubmitButton(
            user = ProfileRequest(
                akun,
                "https://api.fathoor.dev/v1/file/img/default.png",
                newEmail,
                newPassword,
                newAlamat,
                newAlamatLocation.latitude,
                newAlamatLocation.longitude
            ),
            onSave = onSave
        )
    }
}
