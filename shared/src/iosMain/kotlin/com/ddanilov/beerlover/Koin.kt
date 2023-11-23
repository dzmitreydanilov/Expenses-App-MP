package com.ddanilov.beerlover

import com.ddanilov.beerlover.breweries.BreweriesViewModel
import com.ddanilov.beerlover.breweries.details.BreweryDetailsViewModel
import com.ddanilov.beerlover.breweries.favorite.FavoriteBreweryViewModel
import com.ddanilov.beerlover.network.BreweriesListApiService
import org.koin.core.Koin

val Koin.breweriesListApiService: BreweriesListApiService
    get() = get()
val Koin.breweriesViewModel: BreweriesViewModel
    get() = get()
val Koin.favoriteBreweryViewModel: FavoriteBreweryViewModel
    get() = get()
val Koin.breweryDetailsViewModel: BreweryDetailsViewModel
    get() = get()
