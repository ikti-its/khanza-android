package dev.ikti.home.presentation.component.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.CScreen
import dev.ikti.core.domain.model.screen.Nav
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.home.R
import dev.ikti.home.presentation.component.atom.HomeFeatureLabel

@Composable
fun HomeFeatureGrid(
    modifier: Modifier,
    token: String,
    role: String,
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
                            CScreen.Akun.route.replace("{type}", "view")
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
                            CScreen.Kehadiran.route
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
                            CScreen.Pegawai.route.replace("{role}", role).replace("{feature}", "View")
                        )
                    }
                )
                HomeFeatureItem(label = "Inventaris")
            }
            Spacer(modifier = modifier.height(18.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeFeatureItem(label = "Pengadaan")
                HomeFeatureItem(label = "Rawat Inap")
                HomeFeatureItem(label = "Pengobatan")
                HomeFeatureItem(
                    label = "Lainnya",
                    icon = R.drawable.ic_feature_other,
                    onClick = { navController.navigate(Nav.Home.route) }
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeFeatureGridPreview() {
    KhanzaTheme {
        HomeFeatureGrid(
            modifier = Modifier,
            token = "",
            role = "",
            navController = rememberNavController()
        )
    }
}