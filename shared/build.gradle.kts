@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    kotlin("native.cocoapods")
    id("kmp.library")
    kotlin("plugin.serialization")
    id("co.touchlab.skie") version "0.5.5"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
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

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.decompose)
                implementation(libs.koin.core)
                implementation(project(":network"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.koin.android)
                implementation(libs.decompose.extensions.jetpack)
            }
        }

        val iosMain by getting {
            dependencies {
                api(libs.decompose)
                api(libs.koin.core)
            }
        }
    }
}

android {
    namespace = "com.ddanilov.kmpsandbox"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
