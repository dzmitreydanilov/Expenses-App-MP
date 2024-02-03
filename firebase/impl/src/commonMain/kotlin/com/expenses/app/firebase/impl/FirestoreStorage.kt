package com.expenses.app.firebase.impl

import com.expenses.app.firebase.api.Category
import com.expenses.app.firebase.api.Firestore
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class FirestoreStorage : Firestore {

    private val firestore = Firebase.firestore

    override suspend fun getCategories(): List<Category> {
        val items = firestore.collection("/Categories").get()
        val result: List<Category> = items.documents.map {
            it.data()
        }

        return result
    }
}