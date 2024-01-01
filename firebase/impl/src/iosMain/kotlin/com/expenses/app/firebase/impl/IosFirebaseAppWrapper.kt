package com.expenses.app.firebase.impl

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

class IosFirebaseAppWrapper : FirebaseAppWrapper {
    override fun initFirebase() {
        Firebase.initialize()
    }
}

