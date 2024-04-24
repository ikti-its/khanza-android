package dev.ikti.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.ikti.core.R

val FontGilroy = FontFamily(
    Font(R.font.gilroy_light, FontWeight.Light),
    Font(R.font.gilroy_regular, FontWeight.Normal),
    Font(R.font.gilroy_medium, FontWeight.Medium),
    Font(R.font.gilroy_semibold, FontWeight.SemiBold),
    Font(R.font.gilroy_bold, FontWeight.Bold),
)

val typography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 42.sp,
        letterSpacing = 0.sp,
        fontFamily = FontGilroy
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
        fontFamily = FontGilroy
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
        fontFamily = FontGilroy
    ),
    labelMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontGilroy
    )
)