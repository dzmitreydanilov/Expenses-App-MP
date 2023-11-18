package com.ddanilov.beerlover

import com.danilov.network.response.handler.DefaultHttpResponseHandler
import com.danilov.network.response.handler.HttpResponseHandler
import com.ddanilov.beerlover.network.BreweriesListApiService
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val apiServicesModule = module {
    factory<HttpResponseHandler> {
        DefaultHttpResponseHandler()
    }

    factory {
        BreweriesListApiService(get(), get())
    }
}
