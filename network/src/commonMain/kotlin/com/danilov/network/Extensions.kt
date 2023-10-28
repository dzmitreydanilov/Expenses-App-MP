package com.danilov.network

import io.ktor.http.URLBuilder
import io.ktor.http.path
import io.ktor.http.takeFrom

fun URLBuilder.buildUrl(
    baseUrl: String,
    path: String? = null,
    queryParams: Map<String, String> = mapOf()
) {
    takeFrom(baseUrl)
    setQueryParams(queryParams)
    path?.let {
        path(path)
    }
    build()
}

private fun URLBuilder.setQueryParams(params: Map<String, String>) {
    if (params.isNotEmpty()) {
        params.forEach {
            parameters.append(it.key, it.value)
        }
    }
}
