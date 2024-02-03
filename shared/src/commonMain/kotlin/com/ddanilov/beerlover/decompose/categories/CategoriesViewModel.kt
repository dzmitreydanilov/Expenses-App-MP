package com.ddanilov.beerlover.decompose.categories

import com.ddanilov.beerlover.DecomposeViewModel
import com.expenses.api.ExpensesCategory
import com.expenses.category.ExpensesCategoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoryProvider: ExpensesCategory
) : DecomposeViewModel() {

    val state: MutableStateFlow<CategoriesState> = MutableStateFlow(Loading)

    fun getExpensesCategories() {
        coroutineScope.launch {
            state.update { Loading }
            val categories = categoryProvider.getCategories()
            state.update { Loaded(categories) }
        }
    }
}