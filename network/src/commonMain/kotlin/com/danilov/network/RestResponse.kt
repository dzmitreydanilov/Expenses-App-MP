package com.danilov.network

/**
 * A sealed class representing network responses that can be either a successful response or an error response.
 *
 * @param <T> The type of the successful response body.
 * @param <E> The type of the error response.
 */
sealed interface RestResponse<out T> {

    /**
     * Represents successful network responses (2xx).
     */
    data class Success<T>(val body: T) : RestResponse<T>

    sealed class Error(
        open val code: Int?,
        open val errorBody: String?
    ) : RestResponse<Nothing>{

        /**
         * Represents server errors.
         * @param code HTTP Status code
         * @param errorBody Response body
         */
        data class HttpError(
            override val code: Int?,
            override val errorBody: String?,
        ) : Error(code, errorBody)

        /**
         * Represent SerializationExceptions.
         * @param message Detail exception message
         */
        data class SerializationError(
            override val errorBody: String?,
        ) : Error(null, errorBody)

        /**
         * Represent other exceptions.
         * @param message Detail exception message
         */
        data class GenericError(
            override val errorBody: String?,
        ) : Error(null, errorBody)
    }
}
