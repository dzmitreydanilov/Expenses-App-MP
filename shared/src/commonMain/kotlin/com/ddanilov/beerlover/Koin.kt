package com.ddanilov.beerlover

import com.arkivanov.decompose.ComponentContext
import com.ddanilov.beerlover.decompose.expenseslist.KoinScopeComponent
import com.ddanilov.beerlover.di.FeatureApiManager
import com.expenses.app.firebase.impl.FirebaseProvider
import com.expenses.app.firebase.impl.firebaseAppModule
import com.expenses.category.CategoryImpl
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

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
            firebaseAppModule,
            module {
                single(FeatureApiManager.FEATURE_MAP_QUALIFIER) { featureImpl.associateBy { it.qualifier } }
            }
        )
    }
}


// TODO enable/disable network logging for debug/release builds depends on build type
// called by iOS
fun KoinApplication.Companion.start(networkLoggingEnabled: Boolean = false): KoinApplication {
    return initKoin(networkLoggingEnabled = networkLoggingEnabled) {}
}

private val featureImpl = listOf(
    FirebaseProvider,
    CategoryImpl
)

interface CustomDefaultComponentContext : ComponentContext, KoinScopeComponent
