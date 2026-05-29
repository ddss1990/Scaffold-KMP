package com.scaffold.kmp

internal actual fun platformLog(
    level: LogLevel,
    tag: String,
    line: String,
    throwable: Throwable?,
) {
    if (throwable == null) {
        println(line)
    } else {
        println("$line | error=${throwable.message}")
        throwable.printStackTrace()
    }
}
