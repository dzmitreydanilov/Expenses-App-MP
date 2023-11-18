@file:Suppress("OPT_IN_USAGE")

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.multiplatform)
    kotlin("native.cocoapods")
    id("kotlin.detekt")
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "composeApp"
            isStatic = true
            export(project(":shared"))
            export(libs.decompose)
            export(libs.essenty)
        }
    }

    applyDefaultHierarchyTemplate {
        common {
            group("mobile") {
                withIos()
                withAndroidTarget()
            }
        }
    }

    cocoapods {
        version = "1.0.0"
        summary = "Compose application framework"
        homepage = "empty"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(project(":shared"))
                implementation(libs.decompose)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.bundles.compose)
                implementation(libs.androidx.compose.activity)
                implementation(libs.decompose.extensions.jetpack)
                implementation(libs.koin.android)
            }
        }

        iosMain {
            dependencies {
                api(project(":shared"))
                api(libs.decompose)
                api(libs.essenty)
            }
        }
    }
}

android {
    namespace = com.ddanilov.convention.Configs.namespace
    compileSdk = 34
    defaultConfig {
        minSdk = 28
        applicationId = com.ddanilov.convention.Configs.applicationId
    }


    defaultConfig.targetSdk = com.ddanilov.convention.Configs.targetSdk

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
        resources.srcDirs("src/commonMain/resources")
    }
}
