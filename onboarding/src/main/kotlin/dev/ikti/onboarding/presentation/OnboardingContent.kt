package dev.ikti.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
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
    modifier: Modifier,
    type: String = "",
    pages: List<OnboardingPage> = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    ),
    pagerState: PagerState = rememberPagerState { pages.size },
    navigateToLogin: () -> Unit
) {
    MainScaffold(
        modifier = modifier,
        background = Color(0xFFF7F7F7)
    ) {
        val coroutineScope = rememberCoroutineScope()

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(modifier = modifier.offset(y = 150.dp), contentAlignment = Alignment.Center) {
                Box(
                    modifier = modifier
                        .blur(30.dp, BlurredEdgeTreatment.Unbounded)
                        .size(110.dp)
                        .background(Color(0xFFF7F7F7).copy(0.8f), shape = RoundedCornerShape(16.dp))
                ) {}
                Box(
                    modifier = modifier
                        .size(100.dp)
                        .background(Color(0xFFF7F7F7), shape = RoundedCornerShape(16.dp))
                ) {}
                Box(modifier = modifier.size(180.dp), contentAlignment = Alignment.Center) {
                    when (pagerState.currentPage) {
                        0 -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_onboard_logo),
                                contentDescription = null,
                                modifier = modifier.fillMaxSize()
                            )
                        }

                        1 -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_onboard_timer),
                                contentDescription = null,
                                modifier = modifier.size(48.dp)
                            )
                        }

                        2 -> {
                            Image(
                                painter = painterResource(id = R.drawable.ic_onboard_arrow),
                                contentDescription = null,
                                modifier = modifier.size(48.dp)
                            )
                        }
                    }
                }
            }

            Column(modifier = modifier.fillMaxSize()) {
                HorizontalPager(
                    state = pagerState,
                    modifier = modifier.weight(10f)
                ) { page ->
                    Box(modifier = modifier.padding(bottom = 186.dp)) {
                        OnboardingText(onboardingPage = pages[page])
                    }
                }
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                OnboardingIndicator(
                    modifier = modifier.padding(bottom = 16.dp),
                    size = pages.size,
                    selectedIndex = pagerState.currentPage
                )

                OnboardingButton(
                    modifier = modifier.padding(horizontal = 24.dp),
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

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingContentFirstPreview() {
    KhanzaTheme {
        OnboardingContent(
            modifier = Modifier,
            pagerState = rememberPagerState(0) { 3 },
            navigateToLogin = {}
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Preview() {
    KhanzaTheme {
        OnboardingContent(
            modifier = Modifier,
            pagerState = rememberPagerState(1) { 3 },
            navigateToLogin = {}
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingContentThirdPreview() {
    KhanzaTheme {
        OnboardingContent(
            modifier = Modifier,
            pagerState = rememberPagerState(2) { 3 },
            navigateToLogin = {}
        )
    }
}
