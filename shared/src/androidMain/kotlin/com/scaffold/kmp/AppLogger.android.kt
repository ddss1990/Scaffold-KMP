package com.scaffold.kmp

import android.util.Log

internal actual fun platformLog(
    level: LogLevel,
    tag: String,
    line: String,
    throwable: Throwable?,
) {
    when (level) {
        LogLevel.DEBUG -> Log.d(tag, line)
        LogLevel.INFO -> Log.i(tag, line)
        LogLevel.WARN -> Log.w(tag, line)
        LogLevel.ERROR -> {
            if (throwable == null) {
                Log.e(tag, line)
            } else {
                Log.e(tag, line, throwable)
            }
        }
    }
}
