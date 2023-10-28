package com.ddanilov.convention

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

/**
 * Configures the detekt plugin.
 */
internal fun Project.configureDetekt(extension: DetektExtension) {
    extension.apply {
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        parallel = true
        buildUponDefaultConfig = true
        autoCorrect = true
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        exclude("**/**.md")
        autoCorrect = true
    }
}
