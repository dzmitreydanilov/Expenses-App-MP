package com.ddanilov.beerlover.decompose.breweries

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.ddanilov.beerlover.breweries.BreweriesListRepository
import com.ddanilov.beerlover.breweries.BreweriesState
import com.ddanilov.beerlover.breweries.BreweriesViewModel
import com.ddanilov.beerlover.decompose.brewery.BreweryInfoComponent
import com.ddanilov.beerlover.decompose.home.SlotConfig
import com.ddanilov.beerlover.network.BreweriesListApiService
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BreweryListComponent(
    componentContext: ComponentContext
) : BreweryList, ComponentContext by componentContext, KoinComponent {


//    init {
//        lifecycle.subscribe(
//        object : Lifecycle.Callbacks {
//            override fun onDestroy() {
//                super.onDestroy()
//                vm.onDestroy()
//            }
//                /* Component created */
//
//        })
//    }
    private val httpClient: BreweriesListApiService by inject()
//    private val vm = BreweriesViewModel(BreweriesListRepository(httpClient))
//    override val state = vm.breweriesState
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
