package dev.ikti.core.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetSystemUI(
    statusColor: Color = Color.Transparent,
    navigationColor: Color = Color(0xFFFFFFFF),
    lightStatusBar: Boolean = false,
    fullScreen: Boolean = false
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(view) {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)

            window.statusBarColor = statusColor.toArgb()
            window.navigationBarColor = navigationColor.toArgb()
            insetsController.isAppearanceLightStatusBars = lightStatusBar

            if (fullScreen) {
                insetsController.hide(WindowInsetsCompat.Type.navigationBars())
                insetsController.hide(WindowInsetsCompat.Type.statusBars())
                insetsController.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            } else {
                insetsController.show(WindowInsetsCompat.Type.navigationBars())
                insetsController.show(WindowInsetsCompat.Type.statusBars())
            }
        }
    }
}
