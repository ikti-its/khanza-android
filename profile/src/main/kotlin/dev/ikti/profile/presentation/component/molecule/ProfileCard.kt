package dev.ikti.profile.presentation.component.molecule

import android.content.Context
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.core.util.showToast
import dev.ikti.profile.presentation.component.atom.ProfileCardEmail
import dev.ikti.profile.presentation.component.atom.ProfileCardName
import dev.ikti.profile.presentation.component.atom.ProfileCardPhoto

@Composable
fun ProfileCard(
    context: Context = LocalContext.current,
    stateProfile: UIState<UserInfo>,
    navigateToDetail: () -> Unit,
    navigateToEdit: () -> Unit
) {
    Box(
        modifier = Modifier
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            when (stateProfile) {
                is UIState.Success -> {
                    val data = stateProfile.data

                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        ProfileCardPhoto(url = data.foto)
                        Spacer(Modifier.width(16.dp))
                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                            ProfileCardName(name = data.nama)
                            Spacer(Modifier.height(6.dp))
                            ProfileCardEmail(email = data.email)
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Column(Modifier.clickable { navigateToEdit() }) {
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
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Shimmer(
                            height = 42.dp,
                            width = 42.dp,
                            shape = RoundedCornerShape(30.dp),
                            color = Color(0xFF0A2D27)
                        )
                        Spacer(Modifier.width(16.dp))
                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                            Shimmer(
                                height = 20.dp,
                                width = 80.dp,
                                shape = RoundedCornerShape(5.dp),
                                color = Color(0xFF0A2D27)
                            )
                            Spacer(Modifier.height(4.dp))
                            Shimmer(
                                height = 16.dp,
                                width = 100.dp,
                                shape = RoundedCornerShape(5.dp),
                                color = Color(0xFF0A2D27)
                            )
                        }
                    }

                    showToast(context, "Gagal memuat profil")
                }

                else -> {
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Shimmer(
                            height = 42.dp,
                            width = 42.dp,
                            shape = RoundedCornerShape(30.dp),
                            color = Color(0xFF0A2D27)
                        )
                        Spacer(Modifier.width(16.dp))
                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                            Shimmer(
                                height = 20.dp,
                                width = 80.dp,
                                shape = RoundedCornerShape(5.dp),
                                color = Color(0xFF0A2D27)
                            )
                            Spacer(Modifier.height(4.dp))
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
