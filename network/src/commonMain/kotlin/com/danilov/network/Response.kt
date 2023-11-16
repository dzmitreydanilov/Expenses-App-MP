package com.danilov.network

/**
 * A sealed class representing network responses that can be either a successful response or an error response.
 *
 * @param <T> The type of the successful response body.
 * @param <E> The type of the error response.
 */

sealed class Response<out T> {
    /**
     * Represents successful network responses (2xx).
     */
    data class Success<T>(val body: T) : Response<T>()

    sealed class Error : Response<Nothing>() {
        /**
         * Represents server errors.
         * @param code HTTP Status code
         * @param errorBody Response body
         * @param errorMessage Custom error message
         */
        data class HttpError(
            val code: Int,
            val errorBody: String?
        ) : Error()

        /**
         * Represent SerializationExceptions.
         * @param message Detail exception message
         */
        data class SerializationError(
            val message: String?,
        ) : Error()

        /**
         * Represent other exceptions.
         * @param message Detail exception message
         */
        data class GenericError(
            val message: String?,
        ) : Error()
    }
}
