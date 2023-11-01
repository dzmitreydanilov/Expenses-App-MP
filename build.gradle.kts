buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
// Lists all plugins used throughout the project without applying them.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
