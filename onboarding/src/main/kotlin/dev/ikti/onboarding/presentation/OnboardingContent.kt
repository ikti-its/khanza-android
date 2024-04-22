package dev.ikti.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.component.template.MainScaffold
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.presentation.component.molecule.OnboardingButton
import dev.ikti.onboarding.presentation.component.molecule.OnboardingIndicator
import dev.ikti.onboarding.presentation.component.molecule.OnboardingText
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingContent(
    modifier: Modifier,
    navigateToLogin: () -> Unit,
    onSetNewUser: (Boolean) -> Unit
) {
    MainScaffold(
        modifier = modifier,
        background = Color(0xFFF7F7F7)
    ) {
        val pages = listOf(
            dev.ikti.onboarding.presentation.model.OnboardingPage.First,
            dev.ikti.onboarding.presentation.model.OnboardingPage.Second,
            dev.ikti.onboarding.presentation.model.OnboardingPage.Third
        )

        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState { pages.size }

//        Column(modifier = modifier.fillMaxSize()) {
//            OnboardingBackground(modifier = modifier)
//        }

        Column(modifier = modifier.fillMaxSize()) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier.weight(10f)
            ) { page ->
                Box(modifier = modifier.padding(bottom = 186.dp)) {
                    OnboardingText(
                        modifier = modifier,
                        onboardingPage = pages[page]
                    )
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
                    onSetNewUser(false)
                    navigateToLogin()
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun OnboardingContentPreview() {
    KhanzaTheme {
        OnboardingContent(
            modifier = Modifier,
            navigateToLogin = {},
            onSetNewUser = {}
        )
    }
}
