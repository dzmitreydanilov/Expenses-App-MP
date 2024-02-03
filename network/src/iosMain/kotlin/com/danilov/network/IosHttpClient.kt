package com.danilov.network

import com.network.cinterop.IosNetworkHelper
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi

actual val httpEngine: HttpClientEngine = Darwin.create()

@OptIn(ExperimentalForeignApi::class)
class Test {

    var helper: IosNetworkHelper? = null

    init {
        helper = IosNetworkHelper()
    }
}
