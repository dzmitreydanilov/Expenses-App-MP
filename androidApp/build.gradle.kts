import com.ddanilov.convention.Configs
import com.ddanilov.convention.getVersionCode
import com.ddanilov.convention.getVersionName

plugins {
    id("android.application.compose")
    id("android.application")
    id("kotlin.detekt")
}

android {
    namespace = Configs.namespace
    defaultConfig {
        applicationId = Configs.applicationId
        versionCode = getVersionCode()
        versionName = getVersionName()
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.compose.activity)
}
