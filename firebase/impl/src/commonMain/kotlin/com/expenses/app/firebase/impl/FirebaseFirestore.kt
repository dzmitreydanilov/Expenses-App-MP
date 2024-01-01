package com.expenses.app.firebase.impl

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore

fun getFirestore() : FirebaseFirestore {
    return Firebase.firestore
}