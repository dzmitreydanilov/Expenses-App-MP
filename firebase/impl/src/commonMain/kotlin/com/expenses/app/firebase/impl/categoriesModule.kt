package com.expenses.app.firebase.impl

import org.koin.dsl.module

val categoriesModule = module {
    factory {
        ExpensesTypeProvider(get())
    }
}