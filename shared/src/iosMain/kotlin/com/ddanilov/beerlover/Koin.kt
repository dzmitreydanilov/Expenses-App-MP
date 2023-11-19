package com.ddanilov.beerlover

import com.ddanilov.beerlover.breweries.BreweriesViewModel
import com.ddanilov.beerlover.network.BreweriesListApiService
import org.koin.core.Koin
import kotlin.experimental.ExperimentalObjCName

val Koin.breweriesListApiService: BreweriesListApiService
    get() = get()

val Koin.breweriesViewModel: BreweriesViewModel
    get() = get()
