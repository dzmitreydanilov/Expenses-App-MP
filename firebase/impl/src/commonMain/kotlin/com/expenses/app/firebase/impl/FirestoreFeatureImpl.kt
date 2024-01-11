package com.expenses.app.firebase.impl

import com.expenses.core.di.ImplDSL
import com.ddanilov.beerlover.di.resolve
import com.expenses.app.firebase.api.FirebaseApi
import com.expenses.app.firebase.api.Firestore
import com.expenses.core.di.LibraryImpl

object FirestoreFeatureImpl : LibraryImpl(FirebaseApi) {

    override fun ImplDSL.definitions() {
        scoped<Firestore> { resolve(::FirestoreStorage) }
    }
}
