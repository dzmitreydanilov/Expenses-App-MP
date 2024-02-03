package com.ddanilov.beerlover.decompose.categories

import com.ddanilov.beerlover.DecomposeViewModel
import com.expenses.api.ExpensesCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoryProvider: ExpensesCategory
) : DecomposeViewModel() {

    val state: MutableStateFlow<CategoriesState> = MutableStateFlow(
        CategoriesState.Initial
    )

    fun getExpensesCategories() {
        coroutineScope.launch {
            state.update { CategoriesState.Loading(emptyList()) }
            val categories = categoryProvider.getCategories()
            state.update { CategoriesState.Loaded(categories) }
        }
    }
}