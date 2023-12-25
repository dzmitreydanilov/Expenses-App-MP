package com.ddanilov.beerlover.decompose.expenseslist

import com.ddanilov.beerlover.network.BreweriesListApiService

interface ListDependencies {
    val apiService: BreweriesListApiService
}