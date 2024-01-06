package com.expenses.api

interface ExpensesCategory {

    suspend fun getCategories(): List<CategoryApplication>
}

data class CategoryApplication(val id: String)
