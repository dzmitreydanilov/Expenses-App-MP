package com.ddanilov.beerlover

import com.danilov.network.httpEngine
import org.koin.dsl.module

actual fun platformModule() = module {
    single { httpEngine }
    single<ConnectivityProviderHelper> {
        ProviderHelper()
    }
}

class ProviderHelper : ConnectivityProviderHelper {
    override fun registerListener(onNetworkAvailable: () -> Unit, onNetworkLost: () -> Unit) {

    }

    override fun unregisterListener() {
    }
}
