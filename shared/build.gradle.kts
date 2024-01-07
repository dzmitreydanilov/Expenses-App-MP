@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kmp.library")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("co.touchlab.skie") version "0.6.0"
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

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export(libs.decompose)
            export(libs.essenty)
            export(project(":core"))
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(libs.decompose)
                api(libs.koin.core)
                implementation(project(":network"))
                implementation(project(":firebase:impl"))
                implementation(project(":category:impl"))
                api(project(":core"))
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
                api(project(":core"))
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
