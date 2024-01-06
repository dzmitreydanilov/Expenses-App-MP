@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
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


    defaultConfig.targetSdk = com.ddanilov.convention.Configs.targetSdk

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    dependencies {
        implementation(libs.bundles.compose)
        implementation(libs.decompose.extensions.jetpack)
        implementation(libs.koin.android)
        implementation(project(":shared"))
    }

    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
    }
}
