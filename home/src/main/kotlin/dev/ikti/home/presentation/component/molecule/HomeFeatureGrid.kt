package dev.ikti.home.presentation.component.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.ikti.core.domain.model.screen.Nav
import dev.ikti.core.domain.model.screen.Screen
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.util.UIState
import dev.ikti.home.R
import dev.ikti.home.data.model.HomeResponse
import dev.ikti.home.presentation.component.atom.HomeFeatureLabel

@Composable
fun HomeFeatureGrid(
    modifier: Modifier,
    stateHome: UIState<HomeResponse>,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            Row {
                HomeFeatureLabel()
            }
            Spacer(modifier = modifier.height(12.dp))
            when (stateHome) {
                is UIState.Success -> {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HomeFeatureItem(
                            label = "Akun",
                            active = true,
                            color = Color(0xFFACF2E7),
                            icon = R.drawable.ic_feature_c_akun,
                            iconColor = Color.Unspecified,
                            onClick = {
                                navController.navigate(
                                    Screen.Akun.route.replace("{type}", "View")
                                )
                            }
                        )
                        HomeFeatureItem(
                            label = "Kehadiran",
                            active = true,
                            color = Color(0xFFACF2E7),
                            icon = R.drawable.ic_feature_c_kehadiran,
                            iconColor = Color.Unspecified,
                            onClick = {
                                navController.navigate(
                                    Screen.Kehadiran.route.replace("{role}", stateHome.data.role)
                                        .replace("{feature}", "View")
                                )
                            }
                        )
                        HomeFeatureItem(
                            label = "Pegawai",
                            active = true,
                            color = Color(0xFFACF2E7),
                            icon = R.drawable.ic_feature_c_kepegawaian,
                            iconColor = Color.Unspecified,
                            onClick = {
                                navController.navigate(
                                    Screen.Pegawai.route.replace("{role}", stateHome.data.role)
                                        .replace("{feature}", "View")
                                )
                            }
                        )
                        HomeFeatureItem(label = "Inventaris")
                    }
                    Spacer(modifier = modifier.height(18.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        HomeFeatureItem(label = "Pengadaan")
                        HomeFeatureItem(label = "Rawat\nInap")
                        HomeFeatureItem(label = "Pengobatan")
                        HomeFeatureItem(
                            label = "Lainnya",
                            icon = R.drawable.ic_feature_other,
                            onClick = { navController.navigate(Nav.Home.route) }
                        )
                    }
                }

                UIState.Loading -> {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Shimmer(
                            height = 68.dp,
                            width = 68.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                        Shimmer(
                            height = 68.dp,
                            width = 68.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                        Shimmer(
                            height = 68.dp,
                            width = 68.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                        Shimmer(
                            height = 68.dp,
                            width = 68.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                    }
                    Spacer(modifier = modifier.height(36.dp))
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Shimmer(
                            height = 68.dp,
                            width = 68.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                        Shimmer(
                            height = 68.dp,
                            width = 68.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                        Shimmer(
                            height = 68.dp,
                            width = 68.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                        Shimmer(
                            height = 68.dp,
                            width = 68.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF272727)
                        )
                    }
                }

                else -> {}
            }
        }
    }
}
