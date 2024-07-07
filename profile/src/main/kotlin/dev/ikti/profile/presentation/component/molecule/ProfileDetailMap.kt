package dev.ikti.profile.presentation.component.molecule

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import dev.ikti.core.R

@Composable
fun ProfileDetailMap(
    context: Context = LocalContext.current,
    modifier: Modifier,
    alamat: String,
    location: LatLng,
    cameraPositionState: CameraPositionState
) {
    GoogleMap(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp)),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
                LocalContext.current, R.raw.maps
            ),
            mapType = MapType.NORMAL
        ),
        uiSettings = MapUiSettings(
            mapToolbarEnabled = false,
            myLocationButtonEnabled = false,
            zoomControlsEnabled = false,
            compassEnabled = false,
            tiltGesturesEnabled = false
        ),
        onMapClick = {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:${location.latitude},${location.longitude}?z=15&q=${alamat}")
                ).setPackage("com.google.android.apps.maps")
            )
        }
    ) {
        Marker(
            state = MarkerState(
                position = location
            ),
            onClick = {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("geo:${location.latitude},${location.longitude}?z=15&q=${alamat}")
                    ).setPackage("com.google.android.apps.maps")
                )
                true
            },
        )
    }
}
