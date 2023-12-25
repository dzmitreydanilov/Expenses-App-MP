package com.ddanilov.beerlover.decompose.expenseslist

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.ddanilov.beerlover.breweries.BreweriesViewModel
import com.ddanilov.beerlover.decompose.expense.BreweryInfoComponent
import com.ddanilov.beerlover.decompose.home.SlotConfig
import com.ddanilov.beerlover.di.ComponentKoinContext
import org.koin.core.component.KoinComponent

class CategoriesList(
    dependencies: ListDependencies,
    componentContext: ComponentContext,
    private val onNavigateToBreweryDetails: (String) -> Unit
) : BreweryList, ComponentContext by componentContext, KoinComponent {

    val koinContext = instanceKeeper.getOrCreate {
        ComponentKoinContext()
    }

    val scope = koinContext.getOrCreateKoinScope(
        createExpensesListModule(dependencies)
    )

    val vm: BreweriesViewModel = instanceKeeper.getOrCreate {
        scope.get()
    }

    private val slotNavigation = SlotNavigation<SlotConfig>()

    override val childSlot: Value<ChildSlot<*, BreweryList.SlotChild>> = childSlot(
        source = slotNavigation,
        serializer = SlotConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChildSlot
    )

    override fun navigateBreweryDetails(id: String) {
        onNavigateToBreweryDetails(id)
    }

    private fun createChildSlot(
        config: SlotConfig,
        componentContext: ComponentContext
    ): BreweryList.SlotChild {
        return when (config) {
            is SlotConfig.ExpensDetails -> {
                BreweryList.SlotChild.BreweryInfo(
                    BreweryInfoComponent(
                        componentContext = componentContext,
                        onDismiss = slotNavigation::dismiss
                    )
                )
            }
        }
    }
}
