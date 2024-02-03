package com.ddanilov.beerlover

import com.ddanilov.beerlover.breweries.CategoriesListViewModel
import com.ddanilov.beerlover.breweries.favorite.FavoriteBreweryViewModel
import com.ddanilov.beerlover.network.BreweriesListApiService
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.dsl.module

// TODO enable/disable network logging for debug/release builds depends on build type
// called by iOS
fun KoinApplication.Companion.start(
    networkLoggingEnabled: Boolean = false,
): KoinApplication {
    return initKoin(
        networkLoggingEnabled = networkLoggingEnabled,
        platformModules = listOf(
//            module { single { appComponent } }
        ),
        appDeclaration = {}
    )
}

val Koin.breweriesListApiService: BreweriesListApiService
    get() = get()
val Koin.breweriesViewModel: CategoriesListViewModel
    get() = get()
val Koin.favoriteBreweryViewModel: FavoriteBreweryViewModel
    get() = get()
val Koin.networkStatus: NetworkListener
    get() = get()

