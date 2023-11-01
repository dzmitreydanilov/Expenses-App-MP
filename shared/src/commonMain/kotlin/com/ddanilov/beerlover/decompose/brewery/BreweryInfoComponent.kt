package com.ddanilov.beerlover.decompose.brewery

import com.arkivanov.decompose.ComponentContext

class BreweryInfoComponent(
    componentContext: ComponentContext,
    onDismiss : () -> Unit,
) : BreweryInfo, ComponentContext by componentContext {

    override fun onDismiss() {
        TODO("Not yet implemented")
    }
}
