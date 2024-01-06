package com.expenses.app.firebase.api

interface Firestore {

    suspend fun getCategories(): List<Category>
}