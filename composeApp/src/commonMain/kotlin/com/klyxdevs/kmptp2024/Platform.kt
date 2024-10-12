package com.klyxdevs.kmptp2024

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform