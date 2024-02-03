package com.expenses.category

import com.expenses.api.CategoryApplication
import com.expenses.api.ExpensesCategory
import com.expenses.app.firebase.api.Category
import com.expenses.app.firebase.api.Firestore

class ExpensesCategoryProvider(private val firestore: Firestore) : ExpensesCategory {

    override suspend fun getCategories(): List<CategoryApplication> {
        val categories = firestore.getCategories()
        return categories.toApplicationModels()
    }
}


private fun List<Category>.toApplicationModels(): List<CategoryApplication> {
    return map { category ->
        CategoryApplication(
            id = category.id,
            name = category.name,
            description = category.description
        )
    }
}