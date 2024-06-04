package dev.ikti.profile.presentation.component.organism

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.profile.R
import dev.ikti.profile.presentation.component.atom.ProfileSpacer

@Composable
fun ProfileView(
    modifier: Modifier = Modifier,
    token: String = "",
    navigateToDetail: () -> Unit = {},
    onLogout: (String) -> Unit = {}
) {
    Column(modifier.fillMaxSize()) {
        Spacer(modifier.size(32.dp))
        Column(modifier.fillMaxWidth()) {
            Text(
                text = "Akun",
                color = Color(0xFF3D3D3D),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    fontFamily = FontGilroy
                )
            )
        }
        Spacer(modifier.size(5.dp))
        Column(
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { navigateToDetail() }) {
            Box(
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp), Alignment.CenterStart
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_profile_detail),
                            contentDescription = null
                        )
                        Spacer(modifier = modifier.size(8.dp))
                        Text(
                            text = "Detail Profil",
                            color = Color(0xFF3D3D3D),
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                fontFamily = FontGilroy
                            )
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Column {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
        ProfileSpacer()
        Spacer(modifier.size(50.dp))
        Button(
            onClick = {
                onLogout(token)
            },
            modifier = modifier
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
                text = "Keluar",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = FontGilroy
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileViewPreview() {
    KhanzaTheme {
        Column(modifier = Modifier.padding(20.dp)) {
            ProfileView()
        }
    }
}
