package dev.ikti.profile.presentation.component.organism

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun ProfileDetailHeader(
    modifier: Modifier = Modifier,
    url: String = "https://api.fathoor.dev/v1/file/img/default.png"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier
                .size(64.dp)
                .background(color = Color(0xFFACF2E7), shape = CircleShape)
                .border(
                    width = 4.dp,
                    color = Color(0xFFF7F7F7),
                    shape = CircleShape
                )
        )
    }
}

@Preview
@Composable
fun ProfileDetailHeaderPreview() {
    KhanzaTheme {
        ProfileDetailHeader()
    }
}
