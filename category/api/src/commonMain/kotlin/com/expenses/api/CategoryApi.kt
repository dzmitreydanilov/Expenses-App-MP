package com.expenses.api

import com.expenses.core.di.LibraryApi
import com.expenses.core.di.ApiDSL

object CategoryApi : LibraryApi() {

    override fun ApiDSL.definitions() {
        scoped<ExpensesCategory>()
    }
}