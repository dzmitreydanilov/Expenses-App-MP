package com.ddanilov.beerlover.decompose.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.home.HomeComponent
import com.expenses.core.decompose.AppComponentContext
import com.expenses.core.decompose.appChildStack
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: AppComponentContext
) : Root, AppComponentContext by componentContext {

    private val navigation = StackNavigation<RootScreenConfig>()

    override val stack: Value<ChildStack<*, Root.Child>> = appChildStack(
        source = navigation,
        serializer = RootScreenConfig.serializer(),
        handleBackButton = true,
        initialStack = { listOf(RootScreenConfig.Home) },
        childFactory = ::createChild
    )

    private fun createChild(
        config: RootScreenConfig,
        componentContext: AppComponentContext
    ): Root.Child {
        return when (config) {
            is RootScreenConfig.Home -> {
                Root.Child.Home(
                    HomeComponent(componentContext = componentContext)
                )
            }
        }
    }
}

@Serializable
private sealed interface RootScreenConfig {

    @Serializable
    data object Home : RootScreenConfig
}
