package com.ddanilov.beerlover

import org.koin.core.Koin

val Koin.networkStatus: NetworkListener
    get() = get()

