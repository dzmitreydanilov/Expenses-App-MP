package com.ddanilov.beerlover

import io.ktor.http.ContentType
import io.ktor.serialization.Configuration
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.DefaultJson
import kotlinx.serialization.json.Json

internal fun Configuration.plainText(
    json: Json = DefaultJson,
    contentType: ContentType = ContentType.Text.Plain
) {
    serialization(contentType, json)
}

private fun Configuration.serialization(contentType: ContentType, json: Json) {
    register(contentType, KotlinxSerializationConverter(json))
}
