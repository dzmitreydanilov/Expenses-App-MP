package com.ddanilov.beerlover.decompose.root

import com.ddanilov.beerlover.decompose.expenseslist.ListDependencies
import com.ddanilov.beerlover.decompose.home.HomeDependencies
import com.ddanilov.beerlover.network.BreweriesListApiService
import org.koin.dsl.module

val rootModule = module {
//    single {
//        DefaultListDependencies(get())
//    } bind ListDependencies::class
//    single {
//        DefaultHomeDependencies()
//    } bind HomeDependencies::class
}

class DefaultListDependencies(
    override val apiService: BreweriesListApiService
) : ListDependencies

class DefaultHomeDependencies (

) : HomeDependencies