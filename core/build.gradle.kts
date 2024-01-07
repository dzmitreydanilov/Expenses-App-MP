plugins {
    id("kmp.library")
}

kotlin {
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
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.decompose)
        }
        commonTest.dependencies {
        }
    }
}

android {
    namespace = "com.expenses.core"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
