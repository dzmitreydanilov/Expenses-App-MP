package com.expenses.core.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigationSource
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlinx.serialization.KSerializer
import org.koin.core.scope.Scope

class DefaultAppComponentContext(
    componentContext: ComponentContext
) : AppComponentContext, ComponentContext by componentContext {
    override val scope: Scope = createKoinScopeForCurrentLifecycle(this)
}

fun AppComponentContext.childAppContext(
    key: String,
    lifecycle: Lifecycle? = null
): AppComponentContext =
    DefaultAppComponentContext(
        componentContext = childContext(key = key, lifecycle = lifecycle),
    )

fun <C : Any, T : Any> AppComponentContext.appChildStack(
    source: StackNavigationSource<C>,
    serializer: KSerializer<C>?,
    initialStack: () -> List<C>,
    key: String = "DefaultChildStack",
    handleBackButton: Boolean = false,
    childFactory: (configuration: C, AppComponentContext) -> T,
): Value<ChildStack<C, T>> =
    childStack(
        source = source,
        serializer,
        initialStack = initialStack,
        key = key,
        handleBackButton = handleBackButton,
    ) { configuration, componentContext ->
        childFactory(
            configuration,
            DefaultAppComponentContext(
                componentContext = componentContext,
            )
        )
    }
