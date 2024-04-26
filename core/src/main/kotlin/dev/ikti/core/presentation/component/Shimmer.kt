package dev.ikti.core.presentation.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun Shimmer(
    height: Dp,
    width: Dp,
    shape: Shape,
    color: Color = Color(0xFFE8E8E8)
) {
    val transition = rememberInfiniteTransition("shimmer")
    val alpha by transition.animateFloat(
        initialValue = .1f,
        targetValue = .25f,
        animationSpec = infiniteRepeatable(
            tween(1000),
            RepeatMode.Reverse
        ),
        label = "shimmer"
    )

    Box(
        modifier = Modifier
            .height(height)
            .width(width)
            .background(
                color = color.copy(alpha),
                shape = shape
            )
    )
}