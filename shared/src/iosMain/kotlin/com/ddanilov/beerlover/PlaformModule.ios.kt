package com.ddanilov.beerlover

import com.danilov.network.httpEngine
import com.ddanilov.beerlover.breweries.BreweriesViewModel
import com.ddanilov.beerlover.breweries.favorite.FavoriteBreweryViewModel
import org.koin.dsl.module

actual fun platformModule() = module {
    single { httpEngine }

    single {
        BreweriesViewModel(get())
    }
    single {
        FavoriteBreweryViewModel(get())
    }
}
