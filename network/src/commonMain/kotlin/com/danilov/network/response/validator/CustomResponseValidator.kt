package com.danilov.network.response.validator

import io.ktor.client.statement.HttpResponse

interface CustomResponseValidator {

    suspend fun validate(response: HttpResponse)
}
