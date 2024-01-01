package com.expenses.app.firebase.impl

import org.koin.dsl.module

internal actual val firebaseAppModule = module {
    single(createdAtStart = true) {
        AndroidFirebaseAppWrapper(get()).initFirebase()
    }
}

internal actual val firestoreModule = module {
    single {
        getFirestore()
    }
}