@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kmp.library")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp") version "1.9.20-1.0.14"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-21"
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

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export(libs.koin.core)
        }
    }

    sourceSets {
        kotlin.sourceSets.all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }

       commonMain {
            dependencies {
                implementation(libs.decompose)
                api(libs.koin.core)
                implementation(project(":network"))
                api("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-15")
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
    namespace = "com.ddanilov.kmpsandbox"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
