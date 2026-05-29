package com.scaffold.kmp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.composelib.ui.wrapped.ScaffoldWrappedRootScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    AppLogger.i(
        tag = "WebApp",
        event = "AppStart",
        message = "Initializing Compose viewport.",
    )
    ComposeViewport {
        WithFontResourcesLoaded {
            ScaffoldWrappedRootScreen(
                greetingText = Greeting().greet(),
            )
        }
    }
}