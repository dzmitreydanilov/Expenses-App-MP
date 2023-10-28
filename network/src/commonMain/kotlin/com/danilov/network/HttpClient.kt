package com.danilov.network

import com.danilov.network.serializationconfig.plainText
import com.danilov.network.response.validator.CustomResponseValidator
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val TIMEOUT_DURATION = 60_000L

fun createHttpClient(
    engine: HttpClientEngine,
    json: Json,
    networkLoggingEnabled: Boolean,
    requestTimeOutMillis: Long = TIMEOUT_DURATION,
    connectionTimeoutMillis: Long = TIMEOUT_DURATION,
    responseValidator: CustomResponseValidator? = null
): HttpClient {
    return HttpClient(engine) {
        expectSuccess = true

        install(ContentNegotiation) {
            json(json)
            plainText(json)
        }

        HttpResponseValidator {
            validateResponse { response ->
                responseValidator?.validate(response)
            }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = requestTimeOutMillis
            connectTimeoutMillis = connectionTimeoutMillis
        }

        if (networkLoggingEnabled) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }
}

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    prettyPrint = true
    encodeDefaults = true
}
