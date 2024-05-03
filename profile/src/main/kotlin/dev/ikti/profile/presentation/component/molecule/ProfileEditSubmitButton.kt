package dev.ikti.profile.presentation.component.molecule

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.profile.data.model.ProfileRequest

@Composable
fun ProfileEditSubmitButton(
    user: ProfileRequest,
    onSave: (ProfileRequest) -> Unit
) {
    Button(
        onClick = {
            onSave(user)
        },
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonColors(
            containerColor = Color(0xFF0A2D27),
            contentColor = Color(0xFFACF2E7),
            disabledContainerColor = Color(0xFF8A8A8E),
            disabledContentColor = Color(0xFFFFFFFF)
        )
    ) {
        Text(
            text = "Simpan",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            )
        )
    }
}
