package com.ddanilov.beerlover.decompose.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.CustomDefaultComponentContext
import com.ddanilov.beerlover.decompose.expenseslist.CategoriesListComponent
import com.ddanilov.beerlover.decompose.favorite.FavoriteComponent
import kotlinx.serialization.Serializable

class HomeComponent(
    componentContext: CustomDefaultComponentContext
) : Home, ComponentContext by componentContext {


    private val navigation = StackNavigation<HomeScreenConfig>()
    override val stack: Value<ChildStack<*, Home.Child>> = childStack(
        source = navigation,
        serializer = HomeScreenConfig.serializer(),
        initialStack = { listOf(HomeScreenConfig.CategoriesList) },
        childFactory = ::child
    )

    override fun onTabClick(tabs: Home.Tab) {
        when (tabs) {
            Home.Tab.ExpensesHome -> navigation.bringToFront(HomeScreenConfig.CategoriesList)
            Home.Tab.Favorite -> navigation.replaceAll(HomeScreenConfig.Favorite)
        }
    }

    private fun navigateBreweryDetails(id: String) {
        navigation.push(HomeScreenConfig.Details(id))
    }

    private fun child(
        config: HomeScreenConfig,
        componentContext: ComponentContext
    ): Home.Child {
        return when (config) {
            is HomeScreenConfig.CategoriesList -> {
                Home.Child.Breweries(
                    CategoriesListComponent(
                        componentContext = componentContext,
                        onNavigateCategory = ::navigateBreweryDetails
                    )
                )
            }

            is HomeScreenConfig.Favorite -> {
                Home.Child.Favorites(
                    FavoriteComponent(componentContext = componentContext)
                )
            }

            is HomeScreenConfig.Details -> {
                Home.Child.BreweryDetails(config.id)
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
    data object CategoriesList : HomeScreenConfig

    @Serializable
    data object Favorite : HomeScreenConfig

    @Serializable
    data class Details(val id: String) : HomeScreenConfig
}

@Serializable
sealed interface SlotConfig {

    @Serializable
    data object ExpensDetails : SlotConfig
}
