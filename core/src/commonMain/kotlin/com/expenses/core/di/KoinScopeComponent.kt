package com.expenses.core.di

import org.koin.core.scope.Scope

interface DecomposeKoinScopeComponent {

    /**
     * Current Scope in use by the component
     */
    val scope: Scope?

    /**
     * Called before closing a scope, on onDestroy
     */
    fun onCloseScope() {}
}
