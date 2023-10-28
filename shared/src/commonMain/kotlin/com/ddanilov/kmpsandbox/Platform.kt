package com.ddanilov.kmpsandbox

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform