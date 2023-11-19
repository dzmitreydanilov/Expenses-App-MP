package com.ddanilov.beerlover

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    networkLoggingEnabled: Boolean = false,
    appDeclaration: KoinAppDeclaration = {}
): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            networkModule(networkLoggingEnabled),
            apiServicesModule,
            repositoryModule(),
            platformModule(),
            viewModelModule()
        )
    }
}

// TODO enable/disable network logging for debug/release builds depends on build type
// called by iOS
fun KoinApplication.Companion.start(networkLoggingEnabled: Boolean = false): KoinApplication {
    return initKoin(networkLoggingEnabled = networkLoggingEnabled) {}
}

