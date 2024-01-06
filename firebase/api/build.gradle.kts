plugins {
    id("kmp.library")
    kotlin("plugin.serialization")
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()


    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
            implementation(project(":core"))
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
        }
    }
}

android {
    namespace = "com.expenses.app.firebase.api"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
