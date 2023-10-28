package com.ddanilov.beerlover

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
