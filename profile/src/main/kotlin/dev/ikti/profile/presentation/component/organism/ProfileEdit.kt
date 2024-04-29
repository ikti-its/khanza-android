package dev.ikti.profile.presentation.component.organism

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun ProfileEdit(
    modifier: Modifier = Modifier,
    email: String = "user@fathoor.dev",
    role: String = "Developer",
    alamat: String = "Kampus ITS Surabaya"
) {
    Column(modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ubah foto profil",
                modifier = modifier.clickable { },
                style = TextStyle(
                    color = Color(0xFF272727),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    fontFamily = FontGilroy
                )
            )
        }
        Spacer(modifier.size(20.dp))
        TextField(
            value = email,
            onValueChange = {},
            modifier = modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = Color(0xFF272727),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            ),
            label = {
                Text(
                    text = "Email",
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
                focusedContainerColor = Color(0xFFF7F7F7),
                unfocusedContainerColor = Color(0xFFF7F7F7)
            ),
        )
        Spacer(modifier.size(10.dp))
        TextField(
            value = "",
            onValueChange = {},
            modifier = modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = Color(0xFF272727),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            ),
            label = {
                Text(
                    text = "Password",
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
                focusedContainerColor = Color(0xFFF7F7F7),
                unfocusedContainerColor = Color(0xFFF7F7F7)
            ),
        )
        Spacer(modifier.size(10.dp))
        TextField(
            value = role,
            onValueChange = {},
            modifier = modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = Color(0xFF272727),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            ),
            label = {
                Text(
                    text = "Role",
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
                focusedContainerColor = Color(0xFFF7F7F7),
                unfocusedContainerColor = Color(0xFFF7F7F7)
            ),
        )
        Spacer(modifier.size(10.dp))
        TextField(
            value = alamat,
            onValueChange = {},
            modifier = modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = Color(0xFF272727),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontGilroy
            ),
            label = {
                Text(
                    text = "Alamat",
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
                focusedContainerColor = Color(0xFFF7F7F7),
                unfocusedContainerColor = Color(0xFFF7F7F7)
            ),
        )
    }
}

@Preview
@Composable
fun ProfileEditPreview() {
    KhanzaTheme {
        ProfileEdit()
    }
}
