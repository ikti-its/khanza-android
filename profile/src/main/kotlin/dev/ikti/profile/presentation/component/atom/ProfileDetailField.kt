package dev.ikti.profile.presentation.component.atom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
fun ProfileDetailField(
    modifier: Modifier = Modifier,
    field: String = "",
    text: String = ""
) {
    TextField(
        value = text,
        onValueChange = {},
        modifier = modifier.fillMaxWidth(),
        enabled = false,
        textStyle = TextStyle(
            color = Color(0xFF272727),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            fontFamily = FontGilroy
        ),
        label = {
            Text(
                text =
                when (field) {
                    "email" -> "Email"
                    "role" -> "Role"
                    "alamat" -> "Alamat"
                    else -> ""
                },
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    fontFamily = FontGilroy
                )
            )
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color(0xFFF7F7F7)
        ),
    )
}

@Preview
@Composable
fun ProfileDetailFieldPreview() {
    KhanzaTheme {
        ProfileDetailField()
    }
}
