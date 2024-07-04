package dev.ikti.home.presentation.component.atom

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.OMNIATheme
import dev.ikti.home.R

@Composable
fun HomeFeatureItemIcon(icon: Int, iconColor: Color) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = iconColor
    )
}

@Preview
@Composable
fun HomeFeatureItemIconPreview() {
    OMNIATheme {
        HomeFeatureItemIcon(icon = R.drawable.ic_dev, iconColor = Color(0xFF272727))
    }
}