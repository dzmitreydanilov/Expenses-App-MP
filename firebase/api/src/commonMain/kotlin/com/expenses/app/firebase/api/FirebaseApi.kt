package com.expenses.app.firebase.api

import com.expenses.core.di.ApiDSL
import com.expenses.core.di.LibraryApi

object FirebaseApi : LibraryApi() {

    override fun ApiDSL.definitions() {
        scoped<Firestore>()
    }
}
