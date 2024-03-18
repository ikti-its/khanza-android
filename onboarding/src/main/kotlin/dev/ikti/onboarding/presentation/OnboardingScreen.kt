package dev.ikti.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.R
import dev.ikti.onboarding.data.model.OnboardingPage
import dev.ikti.onboarding.presentation.component.OnboardingButton
import dev.ikti.onboarding.presentation.component.OnboardingIndicator
import dev.ikti.onboarding.presentation.component.OnboardingPager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )

    val pagerState = rememberPagerState {
        pages.size
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xff000000)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.onboard),
                contentDescription = null,
                modifier = modifier
                    .fillMaxSize(),
                alignment = Alignment.TopCenter
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier
                    .weight(10f)
            ) { page ->
                OnboardingPager(
                    onboardingPage = pages[page]
                )
            }
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            OnboardingIndicator(
                modifier = modifier
                    .padding(bottom = 16.dp),
                size = pages.size,
                selectedIndex = pagerState.currentPage
            )
            OnboardingButton(
                modifier = modifier
            ) {
                viewModel.setNewUser(false)
                navigateToLogin()
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun OnboardingScreenPreview() {
    KhanzaTheme {
        OnboardingScreen(
            navigateToLogin = {}
        )
    }
}
