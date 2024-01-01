package com.expenses.app.firebase.impl

import android.content.Context
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

class AndroidFirebaseAppWrapper(private val context: Context) : FirebaseAppWrapper {
    override fun initFirebase() {
        val app = Firebase.initialize(context)

        println("XXX app initialized ")
    }
}