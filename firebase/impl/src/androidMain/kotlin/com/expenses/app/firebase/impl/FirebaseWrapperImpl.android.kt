package com.expenses.app.firebase.impl

import android.content.Context
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.initialize

class FirebaseWrapperImpl(private val context: Context) : FirebaseWrapper {
    override fun getFirebaseApp(): FirebaseApp? {
        return Firebase.initialize(context)
    }
}
