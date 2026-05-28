package com.scaffold.kpm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform