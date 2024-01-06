package com.expenses.category

import com.expenses.api.CategoryApplication
import com.expenses.api.ExpensesCategory
import com.expenses.app.firebase.api.Firestore

class ExpensesCategoryProvider(private val firestore: Firestore) : ExpensesCategory {

    override suspend fun getCategories(): List<CategoryApplication> {
        val categories = firestore.getCategories()

        println("XXX first item result: ${categories.firstOrNull()}")
        return emptyList()
    }
}