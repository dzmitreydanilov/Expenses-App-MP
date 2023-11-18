package com.danilov.network.response.handler

import com.danilov.network.HttpExceptions
import com.danilov.network.RestResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerializationException

/**
 * An abstract class for handling HTTP responses and converting them into a custom Response object.
 */
abstract class HttpResponseHandler {

    /**
     * A generic function for handling HTTP requests and converting their responses into a custom Response object.
     *
     * @param <T> The expected success response type.
     * @param <E> The expected error response type.
     * @param request A lambda function that sends an HTTP request and returns an HttpResponse.
     * @return A Response object containing either a success or error response.
     */
    suspend inline fun <reified T> handle(request: () -> HttpResponse): RestResponse<T> {
        return try {
            val response = request.invoke().body<T>()
            RestResponse.Success(response)
        } catch (exception: ClientRequestException) {
            RestResponse.Error.HttpError(
                code = exception.response.status.value,
                errorBody = exception.response.body(),
            )
        } catch (exception: HttpExceptions) {
            RestResponse.Error.HttpError(
                code = exception.response.status.value,
                errorBody = exception.response.body(),
            )
        } catch (e: SerializationException) {
            RestResponse.Error.SerializationError(
                errorBody  = e.message,
            )
        } catch (e: Exception) {
            RestResponse.Error.GenericError(
                errorBody = e.message
            )
        }
    }
}
