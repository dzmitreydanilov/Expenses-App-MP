package com.expenses.app.firebase.impl

import com.ddanilov.beerlover.di.ImplDSL
import com.ddanilov.beerlover.di.resolve
import com.expenses.app.firebase.api.FirebaseApi
import com.expenses.core.di.LibraryImpl

object FirebaseProvider : LibraryImpl(FirebaseApi) {

    override fun ImplDSL.definitions() {
        scoped { resolve(::FirestoreStorage) }
    }
}
