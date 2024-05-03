package dev.ikti.khanza

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.ikti.core.domain.model.screen.ModuleScreen
import dev.ikti.core.presentation.theme.KhanzaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            KhanzaTheme {
                Surface {
                    MainApp(
                        startDestination = ModuleScreen.Splash.route,
                        intentToMap = { uri ->
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(uri)
                            ).also { intent ->
                                try {
                                    intent.setPackage("com.google.android.apps.maps")
                                    startActivity(intent)
                                } catch (e: ActivityNotFoundException) {
                                    startActivity(intent)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
