package com.ddanilov.beerlover.decompose.breweries

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.brewery.BreweryInfoComponent
import com.ddanilov.beerlover.decompose.home.SlotConfig

class BreweryListComponent(
    componentContext: ComponentContext
) : BreweryList, ComponentContext by componentContext {

    private val slotNavigation = SlotNavigation<SlotConfig>()
    override val childSlot: Value<ChildSlot<*, BreweryList.SlotChild>> = childSlot(
        source = slotNavigation,
        serializer = SlotConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChildSlot
    )

    private fun createChildSlot(
        config: SlotConfig,
        componentContext: ComponentContext
    ): BreweryList.SlotChild {
        return when (config) {
            is SlotConfig.Brewery -> {
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
