package dev.ikti.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.R
import dev.ikti.onboarding.presentation.component.molecule.OnboardingButton
import dev.ikti.onboarding.presentation.component.molecule.OnboardingIndicator
import dev.ikti.onboarding.presentation.component.molecule.OnboardingText
import dev.ikti.onboarding.presentation.model.OnboardingPage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingContent(
    type: String,
    navigateToLogin: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    val pagerState = rememberPagerState { pages.size }

    LaunchedEffect(type) {
        if (type == "old") {
            pagerState.scrollToPage(2)
        }
    }

    MainScaffold(background = Color(0xFFF7F7F7)) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier.offset(y = 150.dp), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .blur(30.dp, BlurredEdgeTreatment.Unbounded)
                        .size(110.dp)
                        .background(Color(0xFFF7F7F7).copy(0.8f), shape = RoundedCornerShape(16.dp))
                )
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFFF7F7F7), shape = RoundedCornerShape(16.dp))
                )
                Box(modifier = Modifier.size(180.dp), contentAlignment = Alignment.Center) {
                    when (pagerState.currentPage) {
                        0 -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_onboard_logo),
                                contentDescription = null
                            )
                        }

                        1 -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_onboard_timer),
                                contentDescription = null
                            )
                        }

                        2 -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_onboard_arrow),
                                contentDescription = null
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(10f)
                ) { page ->
                    Box(modifier = Modifier.padding(bottom = 180.dp)) {
                        OnboardingText(onboardingPage = pages[page])
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 40.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                OnboardingIndicator(
                    size = pages.size,
                    selectedIndex = pagerState.currentPage
                )
                Spacer(Modifier.height(40.dp))
                OnboardingButton(
                    selectedIndex = pagerState.currentPage
                ) {
                    if (pagerState.currentPage != 2) {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        navigateToLogin()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OnboardingContentNewPreview() {
    KhanzaTheme {
        OnboardingContent(
            type = "new",
            navigateToLogin = {}
        )
    }
}

@Preview
@Composable
fun OnboardingContentOldreview() {
    KhanzaTheme {
        OnboardingContent(
            type = "old",
            navigateToLogin = {}
        )
    }
}
