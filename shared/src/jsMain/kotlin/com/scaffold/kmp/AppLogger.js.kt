package com.scaffold.kmp

internal actual fun platformLog(
    level: LogLevel,
    tag: String,
    line: String,
    throwable: Throwable?,
) {
    when (level) {
        LogLevel.DEBUG -> println(line)
        LogLevel.INFO -> println(line)
        LogLevel.WARN -> println(line)
        LogLevel.ERROR -> println(
            if (throwable == null) line else "$line | error=${throwable.message}"
        )
    }
}
