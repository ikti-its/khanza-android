package dev.ikti.auth.presentation.component.atom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_EMAIL
import dev.ikti.auth.util.AuthConstant.FIELD_TYPE_PASSWORD
import dev.ikti.core.presentation.theme.FontGilroy

@Composable
fun LoginFormPlaceholder(
    field: String
) {
    when (field) {
        FIELD_TYPE_EMAIL -> {
            Text(
                text = "Email",
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    fontFamily = FontGilroy
                )
            )
        }

        FIELD_TYPE_PASSWORD -> {
            Text(
                text = "Kata sandi",
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    fontFamily = FontGilroy
                )
            )
        }
    }
}

@Preview
@Composable
fun LoginFormPlaceholderEmailPreview() {
    LoginFormPlaceholder(FIELD_TYPE_EMAIL)
}

@Preview
@Composable
fun LoginFormPlaceholderPasswordPreview() {
    LoginFormPlaceholder(FIELD_TYPE_PASSWORD)
}
