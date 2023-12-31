package com.expenses.app.firebase.impl

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.initialize

class FirebaseWrapperImpl : FirebaseWrapper {
    override fun getFirebaseApp(): FirebaseApp? {
        return Firebase.initialize()
    }
}
