package com.expenses.app.firebase.impl

import com.expenses.app.firebase.api.Category
import com.expenses.app.firebase.api.ExpensesCategory
import dev.gitlive.firebase.firestore.FirebaseFirestore

class ExpensesTypeProvider(private val firestore: FirebaseFirestore) : ExpensesCategory {

    override suspend fun getCategories(): List<Category> {
        val items = firestore.collection("/DEFAULT_TYPE").get()
        val result: List<Category> = items.documents.map {
            it.data()
        }

        println("XXX first item result: ${result.firstOrNull()}")
        return emptyList()
    }
}