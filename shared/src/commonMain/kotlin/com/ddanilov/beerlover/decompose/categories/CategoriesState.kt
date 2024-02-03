package com.ddanilov.beerlover.decompose.categories

import com.expenses.api.CategoryApplication

sealed class CategoriesState(open val items: List<CategoryApplication>) {

    object Initial : CategoriesState(emptyList())
    data class Loading(override val items: List<CategoryApplication>) : CategoriesState(items)

    data class Loaded(override val items: List<CategoryApplication>) : CategoriesState(items)
}
