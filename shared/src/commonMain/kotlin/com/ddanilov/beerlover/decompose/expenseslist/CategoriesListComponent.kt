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
import com.expenses.api.CategoryApi
import com.expenses.api.ExpensesCategory
import com.expenses.category.CategoryImpl
import com.expenses.core.decompose.AppComponentContext
import com.expenses.core.decompose.createScopeForCurrentLifecycle
import com.expenses.core.di.getFeatureApiManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.scope.Scope
import kotlin.coroutines.CoroutineContext

class CategoriesListComponent(
    componentContext: AppComponentContext,
    private val onNavigateCategory: (String) -> Unit
) : CategoryList, AppComponentContext by componentContext, KoinComponent {

    override val scope: Scope = createScopeForCurrentLifecycle(this)
    private val provider: ExpensesCategory by inject()
    private val coroutineScope = coroutineScope()

    init {
        getKoin().getFeatureApiManager(CategoryApi).link(scope)
        coroutineScope.launch {
            provider.getCategories()
        }
    }

    private val slotNavigation = SlotNavigation<SlotConfig>()

    override val childSlot: Value<ChildSlot<*, CategoryList.SlotChild>> = childSlot(
        source = slotNavigation,
        serializer = SlotConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChildSlot
    )


    override fun navigateBreweryDetails(id: String) {
        onNavigateCategory(id)
    }

    private fun createChildSlot(
        config: SlotConfig,
        componentContext: ComponentContext
    ): CategoryList.SlotChild {
        return when (config) {
            is SlotConfig.ExpensDetails -> {
                CategoryList.SlotChild.BreweryInfo(
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
