package dev.ikti.auth.presentation.component.atom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginProgress() {
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF0A2D27),
        trackColor = Color(0xFFF7F7F7),
        strokeCap = StrokeCap.Round
    )
}

@Preview
@Composable
fun LoginProgressPreview() {
    LoginProgress()
}
