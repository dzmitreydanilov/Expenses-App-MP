import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.scripting.definitions.StandardScriptDefinition.platform

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kmp.library")

    alias(libs.plugins.test.resources)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

kotlin {
    android()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.bundles.ktorCommon)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(kotlin("test"))
                implementation(libs.ktor.mock.core)
                implementation(libs.ktor.client.core)
                implementation(libs.test.resources)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.bundles.ktorAndroid)
                implementation(kotlin("test-junit"))
            }
        }

        val androidTest by creating {
            dependsOn(commonTest)
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.bundles.ktorIos)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
    // https://matrejek.eu/posts/running-kotlin-tests/
    val testBinary =
        kotlin.targets
            .getByName<KotlinNativeTarget>("iosX64")
            .binaries.getTest("DEBUG")
    val runIosTests by project.tasks.creating(tasks.IosSimulatorTestsTask::class) {
        dependsOn(testBinary.linkTask)
        testExecutable.set(testBinary.outputFile)
        simulatorId.set("95681291-6F63-456C-B59D-F157A32BF45E")
    }
}


android.namespace = "com.itransition.templates.kmp.network"
