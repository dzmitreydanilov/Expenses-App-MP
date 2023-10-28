package com.ddanilov.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler

private const val REPOSITORY_NAME = "GitHubPackages"
private const val REPOSITORY_URI_KEY = "repository_uri"
private const val USERNAME_KEY = "username"
private const val TOKEN_KEY = "token"
private const val PROPERTIES_FILENAME = "github.properties"

fun RepositoryHandler.githubRepo(project: Project) {
    maven {
        name = REPOSITORY_NAME
        url = project.uri(project.getGithubKey(REPOSITORY_URI_KEY))
        credentials {
            username = project.getGithubKey(USERNAME_KEY)
            password = project.getGithubKey(TOKEN_KEY)
        }
    }
}

fun RepositoryHandler.cocoaPodsGithubRepo(project: Project) {

}

private fun Project.getGithubKey(key: String): String {
    return getProjectProperty(key, PROPERTIES_FILENAME) as String
}
