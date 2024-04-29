package dev.ikti.profile.presentation.component.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.core.util.UIState
import dev.ikti.profile.presentation.component.atom.ProfileCardEmail
import dev.ikti.profile.presentation.component.atom.ProfileCardName
import dev.ikti.profile.presentation.component.atom.ProfileCardPhoto

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    stateProfile: UIState<Unit> = UIState.Loading,
    foto: String = "https://api.fathoor.dev/v1/file/img/default.png",
    nama: String = "Pengguna",
    email: String = "user@fathoor.dev",
    navigateToDetail: () -> Unit = {},
    navigateToEdit: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFFACF2E7), RoundedCornerShape(20.dp))
            .border(width = 4.dp, color = Color(0xFFF7F7F7), shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                when (stateProfile) {
                    is UIState.Success -> navigateToDetail()
                    else -> {}
                }
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            when (stateProfile) {
                is UIState.Success -> {
                    Row(
                        modifier = modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        ProfileCardPhoto(url = foto)
                        Spacer(modifier = modifier.size(16.dp))
                        Column(modifier = modifier.padding(vertical = 4.dp)) {
                            ProfileCardName(name = nama)
                            Spacer(modifier = modifier.size(6.dp))
                            ProfileCardEmail(email = email)
                        }
                    }
                    Row(
                        modifier = modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Column(modifier.clickable { navigateToEdit() }) {
                            Text(
                                text = "Ubah",
                                color = Color(0xFF13594E),
                                style = TextStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    fontFamily = FontGilroy
                                )
                            )
                        }
                    }
                }

                is UIState.Error -> {
                    Row(
                        modifier = modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Shimmer(
                            height = 42.dp,
                            width = 42.dp,
                            shape = RoundedCornerShape(30.dp),
                            color = Color(0xFF0A2D27)
                        )
                        Spacer(modifier = modifier.size(16.dp))
                        Column(modifier = modifier.padding(vertical = 4.dp)) {
                            ProfileCardName()
                            Spacer(modifier = modifier.size(6.dp))
                            ProfileCardEmail()
                        }
                    }
                }

                UIState.Loading, UIState.Empty -> {
                    Row(
                        modifier = modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Shimmer(
                            height = 42.dp,
                            width = 42.dp,
                            shape = RoundedCornerShape(30.dp),
                            color = Color(0xFF0A2D27)
                        )
                        Spacer(modifier = modifier.size(16.dp))
                        Column(modifier = modifier.padding(vertical = 4.dp)) {
                            Shimmer(
                                height = 20.dp,
                                width = 80.dp,
                                shape = RoundedCornerShape(5.dp),
                                color = Color(0xFF0A2D27)
                            )
                            Spacer(modifier = modifier.size(4.dp))
                            Shimmer(
                                height = 16.dp,
                                width = 100.dp,
                                shape = RoundedCornerShape(5.dp),
                                color = Color(0xFF0A2D27)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileCardLoadingPreview() {
    KhanzaTheme {
        ProfileCard(
            nama = "IKTI",
            email = "ikti@fathoor.dev"
        )
    }
}

@Preview
@Composable
fun ProfileCardSuccessPreview() {
    KhanzaTheme {
        ProfileCard(
            stateProfile = UIState.Success(Unit),
            nama = "IKTI",
            email = "ikti@fathoor.dev"
        )
    }
}

@Preview
@Composable
fun ProfileCardErrorPreview() {
    KhanzaTheme {
        ProfileCard(
            stateProfile = UIState.Error(""),
            nama = "IKTI",
            email = "ikti@fathoor.dev"
        )
    }
}
