package dev.ikti.profile.presentation

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.ikti.core.domain.model.screen.Screen
import dev.ikti.core.domain.model.user.UserInfo
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.util.UIState
import dev.ikti.profile.R
import dev.ikti.profile.presentation.component.molecule.ProfileCard

@Composable
fun ViewContent(
    stateProfile: UIState<UserInfo>,
    stateLogout: UIState<Unit>,
    onLogout: () -> Unit,
    navController: NavHostController
) {
    MainScaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Halaman Profil",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            fontFamily = FontGilroy
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null,
                            tint = Color(0xFFFFFFFF)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0A2D27),
                    navigationIconContentColor = Color(0xFFFFFFFF),
                    titleContentColor = Color(0xFFFFFFFF)
                )
            )
        },
        background = Color(0xFFF7F7F7)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .background(Color(0xFF0A2D27))
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(Color(0xFFACF2E7))
                    )
                }
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Spacer(Modifier.height(20.dp))
                    ProfileCard(
                        stateProfile = stateProfile,
                        navigateToDetail = {
                            navController.navigate(Screen.Akun.route.replace("{type}", "Detail"))
                        },
                        navigateToEdit = {
                            navController.navigate(Screen.Akun.route.replace("{type}", "Edit"))
                        }
                    )
                }
            }
            when (stateProfile) {
                is UIState.Success -> {
                    Spacer(Modifier.height(32.dp))
                    Column(modifier = Modifier.fillMaxSize()) {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                        ) {
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
                        Spacer(Modifier.height(5.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    navController.navigate(
                                        Screen.Akun.route.replace(
                                            "{type}",
                                            "Detail"
                                        )
                                    )
                                }
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.padding(start = 10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_profile_detail),
                                            contentDescription = null
                                        )
                                        Spacer(Modifier.width(8.dp))
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
                                        modifier = Modifier.padding(end = 10.dp),
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
                        Spacer(
                            Modifier
                                .height(2.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp)
                                .background(Color(0xFFDCDCDC))
                        )
                        Spacer(Modifier.size(50.dp))
                        Button(
                            onClick = {
                                onLogout()
                            },
                            modifier = Modifier
                                .height(48.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0A2D27),
                                contentColor = Color(0xFFACF2E7)
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

                else -> {}
            }
        }
    }

    when (stateLogout) {
        is UIState.Success -> {
            navController.navigate(Screen.Onboarding.route) {
                popUpTo(0) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }

        UIState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFACF2E7),
                    trackColor = Color(0xFF0A2D27),
                    strokeCap = StrokeCap.Round
                )
            }
        }

        else -> {}
    }
}
