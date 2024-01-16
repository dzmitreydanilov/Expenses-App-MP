package tasks.cinterop

internal fun CompileTarget.os() = when (this) {
    CompileTarget.iosX64 -> "iphonesimulator"
    CompileTarget.iosArm64 -> "iphoneos"
    CompileTarget.iosSimulatorArm64 -> "iphonesimulator"
}

internal fun CompileTarget.arch() = when (this) {
    CompileTarget.iosX64 -> "x86_64"
    CompileTarget.iosArm64 -> "arm64"
    CompileTarget.iosSimulatorArm64 -> "arm64"
}

internal fun CompileTarget.simulatorSuffix() = when (this) {
    CompileTarget.iosX64 -> "-simulator"
    CompileTarget.iosArm64 -> ""
    CompileTarget.iosSimulatorArm64 -> "-simulator"
}

internal fun CompileTarget.linkerPlatformVersionName() = when (this) {
    CompileTarget.iosArm64 -> "platform_version ios"
    CompileTarget.iosX64,
    CompileTarget.iosSimulatorArm64 -> "platform_version ios-simulator"
}

internal fun CompileTarget.linkerMinOsVersionName() = when (this) {
    CompileTarget.iosX64 -> "ios_simulator_version_min"
    CompileTarget.iosArm64 -> "ios_version_min"
    CompileTarget.iosSimulatorArm64 -> "ios_simulator_version_min"
}