package dev.ikti.khanza.presentation.component.molecule

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.ikti.core.presentation.theme.FontGilroy
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.khanza.R
import dev.ikti.khanza.presentation.component.atom.HomeHeroNameText
import dev.ikti.khanza.presentation.component.atom.HomeHeroWelcomeText

@Composable
fun HomeHeroCard(
    modifier: Modifier,
    expanded: Boolean = false,
    name: String = "Pengguna"
) {
    var isExpanded by remember { mutableStateOf(expanded) }
    Box {
        Box(
            modifier = modifier
                .animateContentSize(tween(durationMillis = 500, easing = EaseInOutElastic))
                .fillMaxWidth()
                .background(color = Color.Unspecified, shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .clickable { isExpanded = !isExpanded }
                .height(if (isExpanded) 200.dp else 120.dp)
        ) {
            Box {
                Column(
                    modifier = modifier
                        .animateContentSize(tween(durationMillis = 500, easing = EaseInOutElastic))
                        .fillMaxWidth()
                        .fillMaxHeight(if (isExpanded) 1f else 0f)
                        .background(color = Color(0xFF26B29D), shape = RoundedCornerShape(16.dp))
                        .padding(top = 24.dp, bottom = 28.dp, start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Test",
                        color = Color(0xFFACF2E7),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            fontFamily = FontGilroy
                        )
                    )
                }
                Column(
                    modifier = modifier
                        .animateContentSize(tween(durationMillis = 200, easing = EaseInOutElastic))
                        .fillMaxWidth()
                        .fillMaxHeight(if (isExpanded) 0.6f else 1f)
                        .background(color = Color(0xFFACF2E7), shape = RoundedCornerShape(16.dp))
                        .padding(top = 24.dp, bottom = 0.dp, start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    HomeHeroWelcomeText()
                    Spacer(modifier = modifier.height(4.dp))
                    HomeHeroNameText(name)
                    Spacer(modifier = modifier.height(24.dp))
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                            contentDescription = null,
                            modifier = modifier.size(18.dp),
                            tint = Color(0xFF0A2D27)
                        )
                    }
                }
            }
            Box(modifier = modifier.fillMaxWidth().height(120.dp), contentAlignment = Alignment.TopEnd) {
                Image(
                    painter = painterResource(id = R.drawable.home_card),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeHeroCardPreviewNormal() {
    KhanzaTheme {
        HomeHeroCard(Modifier, false)
    }
}

@Preview
@Composable
fun HomeHeroCardPreviewExpanded() {
    KhanzaTheme {
        HomeHeroCard(Modifier, true)
    }
}