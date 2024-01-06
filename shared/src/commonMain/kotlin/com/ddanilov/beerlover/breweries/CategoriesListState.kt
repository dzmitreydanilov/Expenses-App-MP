package com.ddanilov.beerlover.breweries

import com.ddanilov.beerlover.models.Brewery


sealed class CategoriesListState(
    open val categories: List<Brewery>,
    open val isErrorPopup: Boolean = false
) {

    data object Initial : CategoriesListState(emptyList()) {
        override val categories: List<Brewery> = emptyList()
    }

    data class Loading(
        override val categories: List<Brewery>
    ) : CategoriesListState(categories)

    data class Loaded(
        override val categories: List<Brewery>
    ) : CategoriesListState(categories)

    data class Tick(
        val tickValue: String,
        override val isErrorPopup: Boolean,
        override val categories: List<Brewery>
    ) : CategoriesListState(categories, isErrorPopup)

    data class Error(
        val error: Throwable,
        override val isErrorPopup: Boolean,
        override val categories: List<Brewery>
    ) : CategoriesListState(categories, isErrorPopup)
}
