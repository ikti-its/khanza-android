package dev.ikti.onboarding.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.ikti.core.presentation.theme.Khanza50
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.onboarding.data.model.OnboardingPage

@Composable
fun OnboardingPager(
    modifier: Modifier = Modifier,
    onboardingPage: OnboardingPage
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 186.dp
                ),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = onboardingPage.title,
                color = Khanza50,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = modifier.padding(16.dp))
            Text(
                text = onboardingPage.description,
                color = Khanza50,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun OnboardingPagerPreview() {
    KhanzaTheme {
        OnboardingPager(
            onboardingPage = OnboardingPage.First
        )
    }
}
