package com.ddanilov.convention

private const val versionMajor = 0
private const val versionMinor = 0
private const val versionPatch = 2

fun getVersionCode(): Int {
    return versionMajor * 1000000 + versionMinor * 1000 + versionPatch
}

fun getVersionName(): String {
    return "$versionMajor.$versionMinor.$versionPatch"
}
