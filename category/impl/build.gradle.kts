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
            implementation(project(":category:api"))
            implementation(project(":firebase:api"))
            implementation(project(":core"))
        }
        commonTest.dependencies {
        }
    }
}

android {
    namespace = "com.expenses.cateagory"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
