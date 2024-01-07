@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("android.application")
    id("android.application.compose")
    alias(libs.plugins.google.services)
    id("kotlin.detekt")

}

android {
    namespace = com.ddanilov.convention.Configs.namespace
    compileSdk = 34
    defaultConfig {
        minSdk = 28
        applicationId = com.ddanilov.convention.Configs.applicationId
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    defaultConfig.targetSdk = com.ddanilov.convention.Configs.targetSdk
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.decompose.extensions.jetpack)
    implementation(libs.androidx.compose.activity)
    implementation(libs.koin.android)
    implementation(project(":shared"))
}
