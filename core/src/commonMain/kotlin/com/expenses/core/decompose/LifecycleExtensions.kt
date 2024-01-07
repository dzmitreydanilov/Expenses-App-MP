package com.expenses.core.decompose

import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.expenses.core.di.DecomposeKoinScopeComponent
import org.koin.core.component.getScopeId
import org.koin.core.component.getScopeName
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeCallback
import org.koin.mp.KoinPlatform

fun LifecycleOwner.createScopeForCurrentLifecycle(owner: LifecycleOwner): Scope {
    val scope = KoinPlatform.getKoin().getOrCreateScope(getScopeId(), getScopeName(), this)
    scope.registerCallback(object : ScopeCallback {
        override fun onScopeClose(scope: Scope) {
            println("XXXXX onScopeClose")
            (owner as DecomposeKoinScopeComponent).onCloseScope()
        }
    })

    registerScopeForLifecycle(scope)

    return scope
}

internal fun LifecycleOwner.registerScopeForLifecycle(scope: Scope) {
    doOnDestroy {
        scope.close()
    }
}
