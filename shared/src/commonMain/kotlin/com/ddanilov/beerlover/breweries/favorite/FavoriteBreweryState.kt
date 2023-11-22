package com.ddanilov.beerlover.breweries.favorite

import com.ddanilov.beerlover.models.Brewery

sealed class FavoriteBreweryState(
    open val brewery: Brewery? = null
) {

    data object Initial : FavoriteBreweryState() {

        override val brewery: Brewery? = null
    }

    data object Loading : FavoriteBreweryState()

    data class Loaded(override val brewery: Brewery) : FavoriteBreweryState(brewery)

    data class Error(val error: Throwable) : FavoriteBreweryState()
}
