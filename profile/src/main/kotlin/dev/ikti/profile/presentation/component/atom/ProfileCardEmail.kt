package dev.ikti.profile.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy

@Composable
fun ProfileCardEmail(email: String) {
    Text(
        text = email,
        color = Color(0xFF3D3D3D),
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = FontGilroy
        )
    )
}
