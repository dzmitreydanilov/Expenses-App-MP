package com.ddanilov.beerlover.decompose.expenseslist

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.expense.BreweryInfoComponent

interface BreweryList {

//    val state: StateFlow<BreweriesState>
    val childSlot: Value<ChildSlot<*, SlotChild>>

    fun navigateBreweryDetails(id: String)

    sealed class SlotChild {
        class BreweryInfo(val component: BreweryInfoComponent) : SlotChild()
    }
}
