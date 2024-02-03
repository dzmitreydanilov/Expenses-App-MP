package com.ddanilov.beerlover.decompose.categories

import com.expenses.api.CategoryApi
import com.expenses.core.di.ApiDSL
import com.expenses.core.di.FeatureApi
import com.expenses.core.di.FeatureImpl
import com.expenses.core.di.ImplDSL

object SharedApi : FeatureApi() {

    override fun ApiDSL.definitions() {
        scoped<CategoriesViewModel>()
    }
}

object SharedImpl : FeatureImpl(SharedApi) {

    override val dependencies = listOf(CategoryApi)

    override fun ImplDSL.definitions() {
        scoped<CategoriesViewModel> {
            CategoriesViewModel(get())
        }
    }
}