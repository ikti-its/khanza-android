package dev.ikti.core.presentation.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val KhanzaDark = Color(0xff0c0c0c)

// Temporary
val Khanza50 = Color(0xffe9eefd)
val Khanza500 = Color(0xff2758e7)
val Khanza900 = Color(0xff102561)
val KhanzaOnPrimary = Color(0xff0c203c)
val KhanzaNavText = Color(0xff9db2ce)

val KhanzaHomeBrush = Brush.linearGradient(
    colors = listOf(
        Color(0xFF007AFF),
        Color(0xFF30B0C7)
    ),
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 0f)
)
