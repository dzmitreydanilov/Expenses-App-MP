package com.expenses.category

import com.expenses.api.CategoryApi
import com.expenses.api.ExpensesCategory
import com.expenses.app.firebase.api.FirebaseApi
import com.expenses.core.di.FeatureImpl
import com.expenses.core.di.ImplDSL

object CategoryImpl : FeatureImpl(CategoryApi) {

    override val dependencies = listOf(FirebaseApi)

    override fun ImplDSL.definitions() {
        scoped<ExpensesCategory> {
            ExpensesCategoryProvider(firestore = get())
        }
    }
}