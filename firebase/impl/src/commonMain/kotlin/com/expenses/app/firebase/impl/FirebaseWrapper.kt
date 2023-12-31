package com.expenses.app.firebase.impl

import dev.gitlive.firebase.FirebaseApp

interface FirebaseWrapper {

    fun getFirebaseApp() : FirebaseApp?
}