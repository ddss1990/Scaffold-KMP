package com.scaffold.kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.composelib.ui.wrapped.ScaffoldWrappedRootScreen

fun main() = application {
    AppLogger.i(
        tag = "DesktopApp",
        event = "AppStart",
        message = "Desktop window is initializing.",
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMPScaffold",
    ) {
        ScaffoldWrappedRootScreen(
            greetingText = Greeting().greet(),
        )
    }
}