package dev.ikti.profile.presentation.component.atom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme

@Composable
fun ProfileDetailMapLabel() {
    Text(
        text = "Denah Lokasi",
        modifier = Modifier.padding(start = 16.dp),
        style = TextStyle(
            color = Color(0xFF272727),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = FontGilroy
        )
    )
}

@Preview
@Composable
fun ProfileDetailMapLabelPreview() {
    KhanzaTheme {
        ProfileDetailMapLabel()
    }
}
