import com.android.build.api.dsl.LibraryExtension
import com.ddanilov.convention.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class MultiplatformLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinMultiplatform(this)
            }
        }
    }
}
