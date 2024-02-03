package com.ddanilov.beerlover.decompose.expense

import com.arkivanov.decompose.ComponentContext

class CategoryInfoComponent(
    componentContext: ComponentContext,
    onDismiss : () -> Unit,
) : CategoryInfo, ComponentContext by componentContext {

    override fun onDismiss() {
        TODO("Not yet implemented")
    }
}
