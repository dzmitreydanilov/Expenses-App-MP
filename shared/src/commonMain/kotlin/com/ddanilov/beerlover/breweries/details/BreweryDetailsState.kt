package com.ddanilov.beerlover.breweries.details

import com.ddanilov.beerlover.models.Brewery

sealed class BreweryDetailsState(
    open val brewery: Brewery? = null
) {

    data object Initial : BreweryDetailsState() {

        override val brewery: Brewery? = null
    }

    data object Loading : BreweryDetailsState()

    data class Loaded(override val brewery: Brewery) : BreweryDetailsState(brewery)

    data class Error(val error: Throwable) : BreweryDetailsState()

    data class Tick(
        override val brewery: Brewery?
    ) : BreweryDetailsState(brewery)
}
