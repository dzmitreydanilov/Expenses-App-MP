@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kmp.library")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.decompose)
                api(libs.koin.core)
                implementation(project(":network"))
                implementation(project(":firebase:impl"))
            }
        }
        androidMain {
            dependencies {
                implementation(libs.koin.android)
                implementation(libs.decompose.extensions.jetpack)
            }
        }
        iosMain {
            dependencies {
                api(libs.decompose)
                api(libs.koin.core)
            }
        }
    }
}

android {
    namespace = "com.expenses.app.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
