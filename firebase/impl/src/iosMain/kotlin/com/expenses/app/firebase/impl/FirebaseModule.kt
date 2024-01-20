package com.expenses.app.firebase.impl

import org.koin.dsl.module

actual val firebaseAppModule = module {
    // TODO we still need call FirebaseApp.configure() from the swift code
    // TODO but it also works but not always =)
    // TODO investigate the issue
    single(createdAtStart = true) {
//        IosFirebaseAppWrapper().initFirebase()
    }
}
