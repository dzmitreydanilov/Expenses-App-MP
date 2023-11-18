package com.ddanilov.beerlover

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

fun networkModule(networkLoggingEnabled: Boolean) = module {
    single { createJson() }
    single { createHttpClient(get(), get(), networkLoggingEnabled = networkLoggingEnabled) }
}

internal fun createHttpClient(
    engine: HttpClientEngine,
    json: Json,
    networkLoggingEnabled: Boolean
): HttpClient {
    return HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(json)
            plainText(json)
        }
        if (networkLoggingEnabled) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }
}

internal fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    prettyPrint = true
    encodeDefaults = true
}
