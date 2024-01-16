package com.danilov.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import com.ttypic.objclibs.kcrypto.IosNetworkHelper
import kotlinx.cinterop.ExperimentalForeignApi

actual val httpEngine: HttpClientEngine = Darwin.create()


@OptIn(ExperimentalForeignApi::class)
class Test {

    var helper: IosNetworkHelper? = null

    init {
        helper = IosNetworkHelper()
    }
}
