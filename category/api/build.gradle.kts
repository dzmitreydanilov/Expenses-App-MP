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
    ).forEach {
        it.binaries.framework {
            baseName = "api"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(project(":core"))
            implementation(project(":firebase:api"))
        }
        commonTest.dependencies {
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
