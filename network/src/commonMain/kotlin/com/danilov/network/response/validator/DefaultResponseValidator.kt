package com.danilov.network.response.validator

import com.danilov.network.HttpExceptions
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess

/**
 * A default implementation of the CustomResponseValidator interface for validating HTTP responses.
 */
class DefaultResponseValidator : CustomResponseValidator {

    /**
     * Validates an HTTP response and throws an exception if it does not meet the expected criteria.
     *
     * @param response The HTTP response to validate.
     * @throws HttpExceptions If the response is not valid based on custom criteria.
     */
    override suspend fun validate(response: HttpResponse) {
        if (!response.status.isSuccess()) {
            val failureReason = when (response.status) {
                HttpStatusCode.Unauthorized -> "Unauthorized request"
                HttpStatusCode.Forbidden -> "${response.status.value} Missing API key."
                HttpStatusCode.NotFound -> "Invalid Request"
                HttpStatusCode.UpgradeRequired -> "Upgrade to VIP"
                HttpStatusCode.RequestTimeout -> "Network Timeout"
                in HttpStatusCode.InternalServerError..HttpStatusCode.GatewayTimeout ->
                    "${response.status.value} Server Error"

                else -> "Network error!"
            }

            throw HttpExceptions(
                response = response,
                failureReason = failureReason,
                cachedResponseText = response.bodyAsText(),
            )
        }
    }
}
