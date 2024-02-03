package com.ddanilov.beerlover

import com.expenses.app.firebase.impl.FirestoreFeatureImpl
import com.expenses.app.firebase.impl.firebaseAppModule
import com.expenses.category.CategoryImpl
import com.expenses.core.di.FeatureApiManager
import com.expenses.core.di.FeatureImpl
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    networkLoggingEnabled: Boolean = false,
    platformModules: List<Module> = emptyList(),
    appDeclaration: KoinAppDeclaration = {}
): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            platformModules +
                    listOf(
                        networkModule(networkLoggingEnabled),
                        apiServicesModule,
                        repositoryModule(),
                        platformModule(),
                        firebaseAppModule,
                        module {
                            single { NetworkListener(get()) }
                        },
                        module {
                            single(FeatureApiManager.FEATURE_MAP_QUALIFIER) { createMap() }
                        }
                    )
        )
    }
}

private fun createMap(): Map<Qualifier, FeatureImpl> {
    val map = featureImpl.associateBy { it.qualifier }
    return map
}


//// TODO enable/disable network logging for debug/release builds depends on build type
//// called by iOS
//fun KoinApplication.Companion.start(networkLoggingEnabled: Boolean = false): KoinApplication {
//    return initKoin(networkLoggingEnabled = networkLoggingEnabled) {}
//}

private val featureImpl = listOf(
    FirestoreFeatureImpl,
    CategoryImpl
)