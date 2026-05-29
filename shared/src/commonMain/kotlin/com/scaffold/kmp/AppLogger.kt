package com.scaffold.kmp

enum class LogLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
}

fun formatLogLine(
    level: LogLevel,
    tag: String,
    event: String,
    message: String,
): String = "[KMP][${level.name}][$tag][$event] $message"

fun shouldRenderContent(
    fontLoaded: Boolean,
    fallbackElapsed: Boolean,
): Boolean = fontLoaded || fallbackElapsed

internal expect fun platformLog(
    level: LogLevel,
    tag: String,
    line: String,
    throwable: Throwable? = null,
)

object AppLogger {
    fun d(tag: String, event: String, message: String) {
        platformLog(LogLevel.DEBUG, tag, formatLogLine(LogLevel.DEBUG, tag, event, message))
    }

    fun i(tag: String, event: String, message: String) {
        platformLog(LogLevel.INFO, tag, formatLogLine(LogLevel.INFO, tag, event, message))
    }

    fun w(tag: String, event: String, message: String) {
        platformLog(LogLevel.WARN, tag, formatLogLine(LogLevel.WARN, tag, event, message))
    }

    fun e(tag: String, event: String, message: String, throwable: Throwable? = null) {
        platformLog(LogLevel.ERROR, tag, formatLogLine(LogLevel.ERROR, tag, event, message), throwable)
    }
}
