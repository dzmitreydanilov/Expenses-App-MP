plugins {
    id("kmp.library")
    kotlin("plugin.serialization")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "impl"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":firebase:api"))
            implementation("dev.gitlive:firebase-firestore:1.10.4")
            implementation("dev.gitlive:firebase-common:1.10.4")
            implementation(libs.kotlinx.serialization)
        }
        commonTest.dependencies {
        }
    }
}

android {
    namespace = "com.expenses.app.firebase.impl"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
