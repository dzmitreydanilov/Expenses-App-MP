package com.ddanilov.beerlover.breweries

import com.ddanilov.beerlover.models.Brewery


sealed class BreweriesState(
    open val breweries: List<Brewery>
) {

    data object Initial : BreweriesState(emptyList()) {

        override val breweries: List<Brewery> = emptyList()
    }

    data object Loading : BreweriesState(emptyList())

    data class Loaded(
        override val breweries: List<Brewery>
    ) : BreweriesState(breweries)

    data class Error(
        val error: Throwable
    ) : BreweriesState(emptyList())
}
