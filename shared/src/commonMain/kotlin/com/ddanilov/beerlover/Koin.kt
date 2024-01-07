package com.ddanilov.beerlover

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigationSource
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.ddanilov.beerlover.decompose.expenseslist.KoinScopeComponent
import com.ddanilov.beerlover.di.FeatureApiManager
import com.expenses.app.firebase.impl.FirebaseProvider
import com.expenses.app.firebase.impl.firebaseAppModule
import com.expenses.category.CategoryImpl
import kotlinx.serialization.KSerializer
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.scope.Scope
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    networkLoggingEnabled: Boolean = false,
    appDeclaration: KoinAppDeclaration = {}
): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(
            networkModule(networkLoggingEnabled),
            apiServicesModule,
            repositoryModule(),
            platformModule(),
            firebaseAppModule,
            module {
                single(FeatureApiManager.FEATURE_MAP_QUALIFIER) { featureImpl.associateBy { it.qualifier } }
            }
        )
    }
}


// TODO enable/disable network logging for debug/release builds depends on build type
// called by iOS
fun KoinApplication.Companion.start(networkLoggingEnabled: Boolean = false): KoinApplication {
    return initKoin(networkLoggingEnabled = networkLoggingEnabled) {}
}

private val featureImpl = listOf(
    FirebaseProvider,
    CategoryImpl
)

interface AppComponentContext : ComponentContext, KoinScopeComponent

class DefaultAppComponentContext(
    componentContext: ComponentContext
) : AppComponentContext, ComponentContext by componentContext {
    override val scope: Scope? = null
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
