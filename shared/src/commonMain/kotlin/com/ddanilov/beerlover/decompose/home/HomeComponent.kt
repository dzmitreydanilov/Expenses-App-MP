package com.ddanilov.beerlover.decompose.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.breweries.BreweryListComponent
import com.ddanilov.beerlover.decompose.favorite.FavoriteComponent
import kotlinx.serialization.Serializable

class HomeComponent(
    componentContext: ComponentContext
) : Home, ComponentContext by componentContext {

    private val navigation = StackNavigation<HomeScreenConfig>()

    override val stack: Value<ChildStack<*, Home.Child>> = childStack(
        source = navigation,
        serializer = HomeScreenConfig.serializer(),
        initialStack = { listOf(HomeScreenConfig.BreweriesList) },
        childFactory = ::child
    )

    override fun onTabClick(tabs: Home.Tab) {
        when (tabs) {
            Home.Tab.Breweries -> navigation.bringToFront(HomeScreenConfig.BreweriesList)
            Home.Tab.Favorite -> navigation.bringToFront(HomeScreenConfig.Favorite)
        }
    }

    private fun child(
        config: HomeScreenConfig,
        componentContext: ComponentContext
    ): Home.Child {
        return when (config) {
            is HomeScreenConfig.BreweriesList -> {
                Home.Child.Breweries(
                    BreweryListComponent(componentContext = componentContext)
                )
            }

            is HomeScreenConfig.Favorite -> {
                Home.Child.Favorites(
                    FavoriteComponent(componentContext = componentContext)
                )
            }
        }
    }

    override fun onBackClicked() {
        navigation.pop()
    }
}

@Serializable
private sealed interface HomeScreenConfig {

    @Serializable
    data object BreweriesList : HomeScreenConfig

    @Serializable
    data object Favorite : HomeScreenConfig
}

@Serializable
sealed interface SlotConfig {

    @Serializable
    data object Brewery : SlotConfig
}
