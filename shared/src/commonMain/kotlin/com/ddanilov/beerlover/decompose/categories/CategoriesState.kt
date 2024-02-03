package com.ddanilov.beerlover.decompose.categories

import com.expenses.api.CategoryApplication

sealed interface CategoriesState

object Loading : CategoriesState

data class Loaded(val items: List<CategoryApplication>) : CategoriesState
