package dev.ikti.onboarding.presentation.component.organism

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.data.model.OnboardingPage
import dev.ikti.onboarding.presentation.component.atom.OnboardingBackground
import dev.ikti.onboarding.presentation.component.molecule.OnboardingButton
import dev.ikti.onboarding.presentation.component.molecule.OnboardingIndicator
import dev.ikti.onboarding.presentation.component.molecule.OnboardingPager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage(
    modifier: Modifier,
    pages: List<OnboardingPage> = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    ),
    pagerState: PagerState = rememberPagerState { pages.size },
    onSetNewUser: (Boolean) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            OnboardingBackground(modifier = modifier)
        }

        Column(
            modifier = modifier.fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = modifier.weight(10f)
            ) { page ->
                OnboardingPager(
                    modifier = modifier,
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
                modifier = modifier.padding(bottom = 16.dp),
                size = pages.size,
                selectedIndex = pagerState.currentPage
            )
            OnboardingButton(
                modifier = modifier.padding(horizontal = 24.dp),
            ) {
                onSetNewUser(false)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun OnboardingPagePreview() {
    KhanzaTheme {
        OnboardingPage(
            modifier = Modifier,
            onSetNewUser = {}
        )
    }
}
