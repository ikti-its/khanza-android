package dev.ikti.core.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SetSystemUI(statusColor: Color, navigationColor: Color, lightStatusBar: Boolean = false) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(view) {
            val window = (view.context as Activity).window
            window.statusBarColor = statusColor.toArgb()
            window.navigationBarColor = navigationColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                lightStatusBar
        }
    }
}
