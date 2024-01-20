import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kmp.library")
    alias(libs.plugins.test.resources)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    id("cinterop.lib")
}

kotlin {
    applyDefaultHierarchyTemplate()
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.compilations {
            val main by getting {
                cinterops {
                    create("Connectivity")
                }
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(libs.bundles.ktorCommon)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(kotlin("test"))
                implementation(libs.ktor.mock.core)
                implementation(libs.ktor.client.core)
                implementation(libs.test.resources)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.bundles.ktorAndroid)
                implementation(kotlin("test-junit"))
            }
        }

        iosMain {
            dependencies {
                implementation(libs.bundles.ktorIos)
            }
        }
    }
}

KLib {
    create("Connectivity") {
        path = file("native/Connectivity")
        packageName("com.network.cinterop")
    }
}

android.namespace = "com.itransition.templates.kmp.network"
