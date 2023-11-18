package com.ddanilov.beerlover

import com.ddanilov.beerlover.network.BreweriesListApiService
import org.koin.core.Koin

val Koin.breweriesListApiService: BreweriesListApiService
    get() = get()
