package com.scaffold.kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.composelib.ui.wrapped.ScaffoldWrappedRootScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        AppLogger.i(
            tag = "AndroidApp",
            event = "AppStart",
            message = "MainActivity created and Compose content is starting.",
        )

        setContent {
            ScaffoldWrappedRootScreen(
                greetingText = Greeting().greet(),
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    ScaffoldWrappedRootScreen(
        greetingText = Greeting().greet(),
    )
}