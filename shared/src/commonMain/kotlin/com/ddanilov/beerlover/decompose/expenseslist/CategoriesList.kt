package com.ddanilov.beerlover.decompose.expenseslist

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.ddanilov.beerlover.decompose.expense.BreweryInfoComponent
import com.ddanilov.beerlover.decompose.home.SlotConfig
import com.expenses.app.firebase.impl.ExpensesTypeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class CategoriesList(
    componentContext: ComponentContext,
    private val onNavigateToBreweryDetails: (String) -> Unit
) : BreweryList, ComponentContext by componentContext, KoinComponent {

    private val categories: ExpensesTypeProvider by inject()
    private val scope = coroutineScope()
    init {
        scope.launch {
            categories.getCategories()
        }
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

fun LifecycleOwner.coroutineScope(
    context: CoroutineContext = Dispatchers.Main.immediate,
): CoroutineScope {
    val scope = CoroutineScope(context + SupervisorJob())
    lifecycle.doOnDestroy(scope::cancel)

    return scope
}