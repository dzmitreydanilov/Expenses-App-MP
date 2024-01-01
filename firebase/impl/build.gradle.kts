plugins {
    id("kmp.library")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project.dependencies.platform("com.google.firebase:firebase-bom:32.7.0"))
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(project(":firebase:api"))
            implementation(libs.koin.core)
            implementation("dev.gitlive:firebase-firestore:1.10.4")
            implementation("dev.gitlive:firebase-common:1.10.4")
            implementation(libs.kotlinx.serialization)
        }

        androidMain.dependencies {

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
