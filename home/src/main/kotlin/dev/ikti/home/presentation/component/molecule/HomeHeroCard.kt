package dev.ikti.home.presentation.component.molecule

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.component.Shimmer
import dev.ikti.core.util.UIState
import dev.ikti.home.R
import dev.ikti.home.data.model.HomeResponse
import dev.ikti.home.presentation.component.atom.HomeHeroDetailShift
import dev.ikti.home.presentation.component.atom.HomeHeroDetailShiftLabel
import dev.ikti.home.presentation.component.atom.HomeHeroDetailStatus
import dev.ikti.home.presentation.component.atom.HomeHeroDetailStatusIndicator
import dev.ikti.home.presentation.component.atom.HomeHeroDetailStatusLabel
import dev.ikti.home.presentation.component.atom.HomeHeroNameText
import dev.ikti.home.presentation.component.atom.HomeHeroWelcomeText

@Composable
fun HomeHeroCard(
    modifier: Modifier,
    stateHome: UIState<HomeResponse>
) {
    var isExpanded by remember { mutableStateOf(true) }

    LaunchedEffect(stateHome) {
        isExpanded = when (stateHome) {
            is UIState.Success -> true
            else -> false
        }
    }

    Box {
        Box(
            modifier = modifier
                .animateContentSize(spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow))
                .fillMaxWidth()
                .background(color = Color.Unspecified)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    when (stateHome) {
                        is UIState.Success -> isExpanded = !isExpanded
                        else -> {}
                    }
                }
                .height(if (isExpanded) 200.dp else 120.dp)
        ) {
            Box {
                when (stateHome) {
                    is UIState.Success -> {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillMaxHeight(if (isExpanded) 1f else 0f)
                                .background(color = Color(0xFF26B29D))
                                .padding(top = 20.dp, bottom = 22.dp, start = 20.dp, end = 20.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    HomeHeroDetailStatusLabel()
                                    Spacer(modifier = modifier.size(5.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        HomeHeroDetailStatusIndicator(stateHome.data.status)
                                        Spacer(modifier = modifier.size(5.dp))
                                        HomeHeroDetailStatus(stateHome.data.status)
                                    }
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    HomeHeroDetailShiftLabel()
                                    Spacer(modifier = modifier.size(5.dp))
                                    HomeHeroDetailShift(
                                        masuk = stateHome.data.jamMasuk,
                                        pulang = stateHome.data.jamPulang
                                    )
                                }
                            }
                        }
                    }

                    else -> {}
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(if (isExpanded) 0.6f else 1f)
                        .background(
                            color = Color(0xFFACF2E7)
                        )
                ) {}
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(if (isExpanded) 0.6f else 1f)
                        .background(
                            color = Color(0xFFACF2E7),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(top = 32.dp, bottom = 0.dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    HomeHeroWelcomeText()
                    Spacer(modifier = modifier.height(5.dp))
                    when (stateHome) {
                        is UIState.Success -> {
                            HomeHeroNameText(stateHome.data.nama)
                            Spacer(modifier = modifier.height(25.dp))
                            Column(
                                modifier = modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                    contentDescription = null,
                                    modifier = modifier.size(28.dp),
                                    tint = Color(0xFF0A2D27)
                                )
                            }
                        }

                        is UIState.Error -> {}

                        else -> {
                            Shimmer(
                                height = 35.dp,
                                width = 200.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFF26B29D)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_card),
                    contentDescription = null
                )
            }
        }
    }
}
