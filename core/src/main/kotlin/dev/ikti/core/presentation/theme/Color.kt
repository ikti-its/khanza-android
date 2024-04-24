package dev.ikti.core.presentation.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Union100 = Color(0xFFD6F9F3)
val Union200 = Color(0xFFACF2E7)
val Union300 = Color(0xFF83ECDC)
val Union400 = Color(0xFF59E5D0)
val Union500 = Color(0xFF30DFC4)
val Union600 = Color(0xFF26B29D)
val Union700 = Color(0xFF1D8676)
val Union800 = Color(0xFF13594E)
val Union900 = Color(0xFF0A2D27)

//val UnionNavText

val UnionLight = Color(0xFFF7F7F7)
val UnionDark = Color(0xFF272727)

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
