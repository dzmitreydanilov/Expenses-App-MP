package com.ddanilov.beerlover

import com.ddanilov.beerlover.breweries.BreweriesViewModel
import org.koin.dsl.module

actual fun viewModelModule() = module {
    factory {
        BreweriesViewModel(get())
    }
}
