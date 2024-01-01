package com.ddanilov.beerlover

import com.danilov.network.httpEngine
import com.ddanilov.beerlover.breweries.BreweriesViewModel
import com.expenses.app.firebase.impl.AndroidFirebaseAppWrapper
import com.expenses.app.firebase.impl.getFirestore
import org.koin.dsl.module

actual fun platformModule() = module {
    single { httpEngine }
    single {
        BreweriesViewModel(get())
    }
}
