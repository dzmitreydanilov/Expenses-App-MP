package com.expenses.category

import com.ddanilov.beerlover.di.ImplDSL
import com.ddanilov.beerlover.di.resolve
import com.expenses.api.CategoryApi
import com.expenses.api.ExpensesCategory
import com.expenses.app.firebase.api.FirebaseApi
import com.expenses.core.di.AbstractFeatureApi
import com.expenses.core.di.LibraryImpl

object CategoryImpl : LibraryImpl(CategoryApi) {

    override val dependencies: List<AbstractFeatureApi> = listOf(FirebaseApi)

    override fun ImplDSL.definitions() {
        scoped<ExpensesCategory> { resolve(::ExpensesCategoryProvider) }
    }
}