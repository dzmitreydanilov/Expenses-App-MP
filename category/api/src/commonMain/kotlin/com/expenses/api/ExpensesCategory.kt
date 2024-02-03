package com.expenses.api

interface ExpensesCategory {

    suspend fun getCategories(): List<CategoryApplication>
}

data class CategoryApplication(
    val id: String,
    val name: String,
    val description: String
)
