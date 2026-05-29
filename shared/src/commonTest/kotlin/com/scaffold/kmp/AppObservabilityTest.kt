package com.scaffold.kmp

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AppObservabilityTest {

    @Test
    fun renderPolicy_allowsFallbackWhenFontNotLoaded() {
        val shouldRender = shouldRenderContent(
            fontLoaded = false,
            fallbackElapsed = true,
        )
        assertTrue(shouldRender)
    }

    @Test
    fun logFormat_usesUnifiedEnglishPattern() {
        val line = formatLogLine(
            level = LogLevel.INFO,
            tag = "WebApp",
            event = "FontFallbackActivated",
            message = "Rendering content without custom font",
        )
        assertEquals(
            "[KMP][INFO][WebApp][FontFallbackActivated] Rendering content without custom font",
            line,
        )
    }
}
