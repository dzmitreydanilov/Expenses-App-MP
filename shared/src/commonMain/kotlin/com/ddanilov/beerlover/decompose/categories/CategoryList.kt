package com.ddanilov.beerlover.decompose.categories

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.expense.CategoryInfoComponent
import kotlinx.coroutines.flow.StateFlow

interface CategoryList {

    val state: StateFlow<CategoriesState>

    val childSlot: Value<ChildSlot<*, SlotChild>>

    fun navigateToCategoryInfo(id: String)

    sealed class SlotChild {
        class BreweryInfo(val component: CategoryInfoComponent) : SlotChild()
    }
}
