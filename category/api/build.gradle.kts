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
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(project(":core"))
            implementation(project(":firebase:api"))
        }
    }
}

android {
    namespace = "com.expenses.api"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}
