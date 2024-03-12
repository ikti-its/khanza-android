package dev.ikti.khanza.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.ikti.core.presentation.theme.KhanzaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Splash screen with new user onboarding
        setContent {
            KhanzaTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}
