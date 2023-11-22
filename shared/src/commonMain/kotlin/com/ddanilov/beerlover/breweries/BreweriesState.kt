package com.ddanilov.beerlover.breweries

import com.ddanilov.beerlover.models.Brewery


sealed class BreweriesState(
    open val breweries: List<Brewery>,
    open val isErrorPopup: Boolean = false
) {

    data object Initial : BreweriesState(emptyList()) {
        override val breweries: List<Brewery> = emptyList()
    }

    data class Loading(
        override val breweries: List<Brewery>
    ) : BreweriesState(breweries)

    data class Loaded(
        override val breweries: List<Brewery>
    ) : BreweriesState(breweries)

    data class Tick(
        val tickValue: String,
        override val isErrorPopup: Boolean,
        override val breweries: List<Brewery>
    ) : BreweriesState(breweries, isErrorPopup)

    data class Error(
        val error: Throwable,
        override val isErrorPopup: Boolean,
        override val breweries: List<Brewery>
    ) : BreweriesState(breweries, isErrorPopup)
}
