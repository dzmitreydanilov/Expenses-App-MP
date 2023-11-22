package com.ddanilov.beerlover

import com.ddanilov.beerlover.breweries.BreweriesListRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun repositoryModule() = module {
    singleOf(::BreweriesListRepository)
}
