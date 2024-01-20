package com.ddanilov.beerlover

import com.danilov.network.Test
import kotlinx.cinterop.ExperimentalForeignApi

class IosApplicationComponent(val networkHelper: ConnectivityProviderHelper)

@OptIn(ExperimentalForeignApi::class)

class TestClass : ConnectivityProviderHelper {

    private val test = Test().helper
    override fun registerListener(onNetworkAvailable: () -> Unit, onNetworkLost: () -> Unit) {
        test?.registerListenerOnNetworkAvailable(onNetworkAvailable, onNetworkLost)
    }

    override fun unregisterListener() {
        test?.unregisterListener()
    }
}