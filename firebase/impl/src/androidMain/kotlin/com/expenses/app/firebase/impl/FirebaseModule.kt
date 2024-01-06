package com.expenses.app.firebase.impl

import org.koin.dsl.module

actual val firebaseAppModule = module {
    single(createdAtStart = true) {
        AndroidFirebaseAppWrapper(get()).initFirebase()
    }
}
