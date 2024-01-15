package com.danilov.network.base

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    val error: String? = null
    val login: Int? = null
}