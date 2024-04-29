package dev.ikti.profile.presentation.component.atom

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun ProfileCardPhoto(
    modifier: Modifier = Modifier,
    url: String = "https://api.fathoor.dev/v1/file/img/default.png"
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier.size(42.dp)
    )
}

@Preview
@Composable
fun ProfileCardPhotoPreview() {
    KhanzaTheme {
        ProfileCardPhoto()
    }
}