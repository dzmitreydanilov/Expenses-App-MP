package com.ddanilov.beerlover.decompose.categories

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.expense.CategoryInfoComponent
import com.ddanilov.beerlover.decompose.home.SlotConfig
import com.ddanilov.beerlover.inject
import com.expenses.api.CategoryApi
import com.expenses.core.decompose.AppComponentContext
import com.expenses.core.decompose.createKoinScopeForCurrentLifecycle
import com.expenses.core.di.getFeatureApiManager
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.mp.KoinPlatform.getKoin

class CategoriesListComponent(
    componentContext: AppComponentContext,
    private val onNavigateCategory: (String) -> Unit
) : CategoryList, AppComponentContext by componentContext {

    private val viewModel: CategoriesViewModel by inject()

    init {
       getKoin().getFeatureApiManager(SharedApi)
            .link(scope ?: createKoinScopeForCurrentLifecycle(this))
        viewModel.getExpensesCategories()
    }

    override val state: StateFlow<CategoriesState> = viewModel.state

    private val slotNavigation = SlotNavigation<SlotConfig>()

    override val childSlot: Value<ChildSlot<*, CategoryList.SlotChild>> = childSlot(
        source = slotNavigation,
        serializer = SlotConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChildSlot
    )

    override fun navigateToCategoryInfo(id: String) {
        onNavigateCategory(id)
    }

    private fun createChildSlot(
        config: SlotConfig,
        componentContext: ComponentContext
    ): CategoryList.SlotChild {
        return when (config) {
            is SlotConfig.ExpensDetails -> {
                CategoryList.SlotChild.BreweryInfo(
                    CategoryInfoComponent(
                        componentContext = componentContext,
                        onDismiss = slotNavigation::dismiss
                    )
                )
            }
        }
    }
}
