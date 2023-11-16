package com.danilov.network.base

import com.danilov.network.Response
import com.danilov.network.response.handler.HttpResponseHandler
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.URLBuilder
import io.ktor.http.contentType
import io.ktor.util.StringValues

abstract class ApiService(
    protected val httpClient: HttpClient,
    protected val responseHandler: HttpResponseHandler
) {

    open val defaultHeaders: StringValues = StringValues.build {}

    protected suspend inline fun <reified T> get(
        body: Any? = null,
        contentType: ContentType = ContentType.Application.Json,
        noinline urlBuilder: URLBuilder.(URLBuilder) -> Unit
    ): Response<T> {
        return handleResponse {
            httpClient.get(
                httpRequestBuilder(
                    body = body,
                    urlBuilder = urlBuilder,
                    contentType = contentType
                )
            )
        }
    }

    protected suspend inline fun <reified T, reified E> post(
        body: Any?,
        contentType: ContentType = ContentType.Application.Json,
        noinline urlBuilder: URLBuilder.(URLBuilder) -> Unit
    ): Response<T> {
        return handleResponse {
            httpClient.post(
                httpRequestBuilder(
                    body = body,
                    urlBuilder = urlBuilder,
                    contentType = contentType
                )
            )
        }
    }

    protected suspend inline fun <reified T, reified E> put(
        body: Any?,
        contentType: ContentType = ContentType.Application.Json,
        noinline urlBuilder: URLBuilder.(URLBuilder) -> Unit
    ): Response<T> {
        return handleResponse {
            httpClient.put(
                httpRequestBuilder(
                    body = body,
                    urlBuilder = urlBuilder,
                    contentType = contentType
                )
            )
        }
    }

    protected fun httpRequestBuilder(
        urlBuilder: URLBuilder.(URLBuilder) -> Unit,
        contentType: ContentType,
        body: Any? = null,
        headers: StringValues? = null
    ): HttpRequestBuilder.() -> Unit = {
        url(urlBuilder)
        contentType(contentType)
        accept(ContentType.Application.Json)
        body?.let { setBody(it) }
        headers {
            headers?.let { appendAll(headers) }
            appendAll(defaultHeaders)
        }
    }

    protected suspend inline fun <reified T> handleResponse(
        httpRequest: () -> HttpResponse
    ): Response<T> {
        return responseHandler.handle(httpRequest)
    }
}
