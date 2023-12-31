plugins {
    id("kmp.library")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()


    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
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
