enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "ExpensesApp"
include(":shared")
include(":network")
include(":androidApp")

include(":firebase")
include(":firebase:api")
include(":firebase:impl")

include(":core")

include(":category")
include(":category:impl")
include(":category:api")
