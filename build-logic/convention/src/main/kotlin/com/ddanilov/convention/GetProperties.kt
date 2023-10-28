package com.ddanilov.convention

import org.gradle.api.Project
import java.io.File

fun Project.getRootProjectProperty(key: String, fileName: String): Any {
    val properties = java.util.Properties()
    val projectDir = rootProject.projectDir
    val localProperties = File(projectDir, fileName)
    if (localProperties.isFile) {
        java.io.InputStreamReader(java.io.FileInputStream(localProperties), Charsets.UTF_8)
            .use { reader ->
                properties.load(reader)
            }
    } else {
        error("File $fileName not found in the $projectDir dir")
    }

    return properties.getProperty(key)
}
fun Project.getProjectProperty(key: String, fileName: String): Any {
    val properties = java.util.Properties()
    val projectDir = project.rootDir
    val localPropertiesFile = File(projectDir, fileName)
    if (localPropertiesFile.isFile) {
        localPropertiesFile.inputStream().use { reader ->
            properties.load(reader)
        }
    } else {
        error("File $fileName not found in the $projectDir dir")
    }

    return properties.getProperty(key)
}

