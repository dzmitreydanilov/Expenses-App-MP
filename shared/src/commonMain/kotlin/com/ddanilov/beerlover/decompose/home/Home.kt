package com.ddanilov.beerlover.decompose.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.breweries.BreweryList
import com.ddanilov.beerlover.decompose.favorite.Favorite

interface Home {

    val stack: Value<ChildStack<*, Child>>

    fun onTabClick(tabs: Tab)

    enum class Tab {
        Breweries, Favorite
    }

    fun onBackClicked()

    sealed class Child {
        class Breweries(val component: BreweryList) : Child()

        class Favorites(val component: Favorite) : Child()
    }
}