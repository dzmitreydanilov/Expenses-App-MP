package com.expenses.api

import com.expenses.core.di.ApiDSL
import com.expenses.core.di.FeatureApi

object CategoryApi : FeatureApi() {

    override fun ApiDSL.definitions() {
        scoped<ExpensesCategory>()
    }
}