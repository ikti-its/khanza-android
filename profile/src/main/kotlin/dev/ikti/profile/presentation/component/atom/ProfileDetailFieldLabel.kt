package dev.ikti.profile.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy

@Composable
fun ProfileDetailFieldLabel(field: String) {
    Text(
        text =
        when (field) {
            "email" -> "Email"
            "role" -> "Role"
            "alamat" -> "Alamat Lengkap"
            "kota" -> "Kota"
            "kode_pos" -> "Kode Pos"
            else -> ""
        },
        style = TextStyle(
            color = Color(0xFF272727),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            fontFamily = FontGilroy
        )
    )
}
