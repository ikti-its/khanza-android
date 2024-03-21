package dev.ikti.onboarding.presentation

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.ikti.core.presentation.theme.KhanzaDark
import dev.ikti.core.presentation.theme.KhanzaTheme
import dev.ikti.core.util.State
import dev.ikti.onboarding.presentation.component.atom.OnboardingProgress
import dev.ikti.onboarding.presentation.component.atom.OnboardingToast
import dev.ikti.onboarding.presentation.component.organism.OnboardingPage
import dev.ikti.onboarding.util.OnboardingConstant.ERR_FAILED_TO_SET_USER
import dev.ikti.onboarding.util.OnboardingConstant.ERR_UNKNOWN_ERROR

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingContent(
    context: Context = LocalContext.current,
    modifier: Modifier,
    stateOnboarding: State<Boolean>,
    navigateToLogin: () -> Unit,
    onSetNewUser: (Boolean) -> Unit
) {
    Scaffold(
        modifier = modifier
    ) {
        Surface(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            color = Color(0xff000000)
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                stateOnboarding.let { state ->
                    when (state) {
                        is State.Success -> {
                            navigateToLogin()
                        }

                        is State.Error -> {
                            when (state.error) {
                                ERR_FAILED_TO_SET_USER -> {
                                    OnboardingToast(context = context, type = ERR_FAILED_TO_SET_USER)
                                }

                                else -> {
                                    OnboardingToast(context = context, type = ERR_UNKNOWN_ERROR)
                                }
                            }
                        }

                        State.Loading -> {
                            Box(
                                modifier = modifier
                                    .fillMaxSize()
                                    .background(KhanzaDark.copy(alpha = 0.5f)),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                OnboardingProgress(modifier = modifier)
                            }
                        }

                        State.Empty -> {
                            OnboardingPage(
                                modifier = modifier,
                                onSetNewUser = onSetNewUser
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun OnboardingContentPreview() {
    KhanzaTheme {
        OnboardingContent(
            modifier = Modifier,
            stateOnboarding = State.Empty,
            navigateToLogin = {},
            onSetNewUser = {}
        )
    }
}
