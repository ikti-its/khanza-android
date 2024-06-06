package dev.ikti.profile.presentation.component.organism

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.ikti.core.util.UIState

@Composable
fun ProfileDetailHeader(
    modifier: Modifier = Modifier,
    stateUpload: UIState<String> = UIState.Empty,
    url: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .size(72.dp)
                .background(color = Color(0xFFF7F7F7), shape = CircleShape)
        )
        SubcomposeAsyncImage(
            model = when (stateUpload) {
                is UIState.Success -> stateUpload.data
                else -> url
            },
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(64.dp)
                .clip(CircleShape)
        )
    }
}
