package dev.ikti.profile.presentation.component.organism

import android.Manifest
import android.net.Uri
import android.view.MotionEvent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import dev.ikti.core.util.UIState
import dev.ikti.core.util.file.FileConstant.ERR_UNSUPPORTED
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
    stateUpload: UIState<String> = UIState.Empty,
    akun: String,
    email: String,
    role: String,
    alamat: String,
    alamatLat: Double,
    alamatLon: Double,
    onSave: (ProfileRequest) -> Unit = {},
    onUpload: (Uri) -> Unit = {}
) {
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    var newFoto by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf(email) }
    var newPassword by remember { mutableStateOf("") }
    var newAlamat by remember { mutableStateOf(alamat) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    var newAlamatLocation by remember { mutableStateOf(LatLng(alamatLat, alamatLon)) }

    val scrollState = rememberScrollState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newAlamatLocation, 15f)
    }
    var columnScrollingEnabled by remember { mutableStateOf(true) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                onUpload(uri)
            }
        }
    )

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
                modifier = modifier.clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
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
                newFoto,
                if (newEmail == "") email else newEmail,
                newPassword,
                if (newAlamat == "") alamat else newAlamat,
                if (newAlamatLocation.latitude == 0.0) alamatLat else newAlamatLocation.latitude,
                if (newAlamatLocation.longitude == 0.0) alamatLon else newAlamatLocation.longitude
            ),
            onSave = onSave
        )

        when (stateUpload) {
            is UIState.Success -> newFoto = stateUpload.data
            is UIState.Error -> {
                when (stateUpload.error) {
                    ERR_UNSUPPORTED -> {
                        // TODO: Error popup
                    }

                    else -> {}
                }
            }

            else -> {}
        }
    }
}
