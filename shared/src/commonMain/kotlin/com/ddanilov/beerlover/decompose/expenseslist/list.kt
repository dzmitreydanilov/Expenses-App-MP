package com.ddanilov.beerlover.decompose.expenseslist

import com.ddanilov.beerlover.breweries.BreweriesListRepository
import com.ddanilov.beerlover.breweries.BreweriesViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun createExpensesListModule(dependencies: ListDependencies): List<Module> {
    return listOf(
        module {
            factory {
                BreweriesListRepository(get())
            }
            factory {
                BreweriesViewModel(get())
            }
        },

        module {
            factory {
                dependencies.apiService
            }
        }
    )
}