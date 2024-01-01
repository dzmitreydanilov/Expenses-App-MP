package com.expenses.app.firebase.impl

import org.koin.core.module.Module
import org.koin.dsl.module

val firebaseModule = module {
    includes(firebaseAppModule, firestoreModule)
}

internal expect val firebaseAppModule: Module

internal expect val firestoreModule: Module