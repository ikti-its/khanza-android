package dev.ikti.khanza.presentation.component.molecule

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.ikti.core.domain.model.screen.Screen
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.presentation.component.atom.MainBottomFABIcon

@Composable
fun MainBottomFAB(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Screen.Kehadiran.route.replace("{type}", "Presensi"))
        },
        modifier = modifier.size(70.dp),
        shape = CircleShape,
        containerColor = Color(0xFF0A2D27),
        contentColor = Color(0xFFACF2E7),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 3.dp,
            pressedElevation = 5.dp
        )
    ) {
        MainBottomFABIcon(modifier)
    }
}

@Preview
@Composable
fun MainBottomFABPreview() {
    KhanzaTheme {
        MainBottomFAB()
    }
}
