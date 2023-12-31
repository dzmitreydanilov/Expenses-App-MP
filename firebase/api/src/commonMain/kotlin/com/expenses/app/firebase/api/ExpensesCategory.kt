package com.expenses.app.firebase.api

interface ExpensesCategory {

    suspend fun getCategories(): List<Category>
}