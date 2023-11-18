package com.ddanilov.beerlover.decompose.breweries

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.breweries.BreweriesState
import com.ddanilov.beerlover.decompose.brewery.BreweryInfoComponent
import kotlinx.coroutines.flow.StateFlow

interface BreweryList {

    val state: StateFlow<BreweriesState>
    val childSlot: Value<ChildSlot<*, SlotChild>>

    sealed class SlotChild {
        class BreweryInfo(val component: BreweryInfoComponent) : SlotChild()
    }
}
