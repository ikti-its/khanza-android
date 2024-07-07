package dev.ikti.home.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.OMNIATheme

@Composable
fun HomeFeatureItemLabel(label: String) {
    Text(
        text = label,
        color = Color(0xFF0C203C),
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = FontGilroy
        ),
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview
@Composable
fun HomeFeatureItemLabelPreview() {
    OMNIATheme {
        HomeFeatureItemLabel(label = "DEV")
    }
}