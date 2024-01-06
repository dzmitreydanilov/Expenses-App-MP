package com.ddanilov.beerlover

import com.ddanilov.beerlover.breweries.CategoriesListViewModel
import com.ddanilov.beerlover.breweries.favorite.FavoriteBreweryViewModel
import com.ddanilov.beerlover.network.BreweriesListApiService
import org.koin.core.Koin

val Koin.breweriesListApiService: BreweriesListApiService
    get() = get()
val Koin.breweriesViewModel: CategoriesListViewModel
    get() = get()
val Koin.favoriteBreweryViewModel: FavoriteBreweryViewModel
    get() = get()
