package com.ddanilov.beerlover

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryOwner
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.InstanceKeeperDispatcher
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.asEssentyLifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import org.koin.core.scope.Scope

fun <T> T.myDefaultComponentContext(): MyDefaultComponentContext where
        T : SavedStateRegistryOwner, T : OnBackPressedDispatcherOwner, T : ViewModelStoreOwner, T : LifecycleOwner =
    MyDefaultComponentContext(
        lifecycle = (this as LifecycleOwner).lifecycle,
        savedStateRegistry = savedStateRegistry,
        viewModelStore = viewModelStore,
        onBackPressedDispatcher = onBackPressedDispatcher,
    )


fun MyDefaultComponentContext(
    lifecycle: androidx.lifecycle.Lifecycle,
    savedStateRegistry: SavedStateRegistry? = null,
    viewModelStore: ViewModelStore? = null,
    onBackPressedDispatcher: OnBackPressedDispatcher? = null,
): MyDefaultComponentContext =
    MyDefaultComponentContext(
        lifecycle = lifecycle.asEssentyLifecycle(),
        stateKeeper = savedStateRegistry?.let(::StateKeeper),
        instanceKeeper = viewModelStore?.let(::InstanceKeeper),
        backHandler = onBackPressedDispatcher?.let(::BackHandler),
    )

class MyDefaultComponentContext(
    override val lifecycle: Lifecycle,
    stateKeeper: StateKeeper? = null,
    instanceKeeper: InstanceKeeper? = null,
    backHandler: BackHandler? = null,
    koinScope: Scope? = null
) : AppComponentContext {

    override val stateKeeper: StateKeeper = stateKeeper ?: StateKeeperDispatcher()
    override val instanceKeeper: InstanceKeeper =
        instanceKeeper ?: InstanceKeeperDispatcher().attachTo(lifecycle)
    override val backHandler: BackHandler = backHandler ?: BackDispatcher()
    override val scope: Scope? = null

    constructor(lifecycle: Lifecycle) : this(
        lifecycle = lifecycle,
        stateKeeper = null,
        instanceKeeper = null,
        backHandler = null,
    )
}

internal fun InstanceKeeperDispatcher.attachTo(lifecycle: Lifecycle): InstanceKeeperDispatcher {
    lifecycle.doOnDestroy(::destroy)
    return this
}
