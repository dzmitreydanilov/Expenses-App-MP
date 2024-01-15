package com.ddanilov.beerlover.decompose.expenseslist

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.ddanilov.beerlover.NetworkListener
import com.ddanilov.beerlover.decompose.expense.BreweryInfoComponent
import com.ddanilov.beerlover.decompose.home.SlotConfig
import com.expenses.api.CategoryApi
import com.expenses.api.ExpensesCategory
import com.expenses.core.decompose.AppComponentContext
import com.expenses.core.decompose.createKoinScopeForCurrentLifecycle
import com.expenses.core.di.DecomposeKoinScopeComponent
import com.expenses.core.di.getFeatureApiManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatform.getKoin
import org.koin.mp.KoinPlatformTools
import kotlin.coroutines.CoroutineContext

class CategoriesListComponent(
    componentContext: AppComponentContext,
    private val onNavigateCategory: (String) -> Unit
) : CategoryList, AppComponentContext by componentContext {

    private val provider: ExpensesCategory by inject()
    private val connectivity: NetworkListener by inject()
    private val coroutineScope = coroutineScope()

    init {
        getKoin().getFeatureApiManager(CategoryApi)
            .link(scope ?: createKoinScopeForCurrentLifecycle(this))
        coroutineScope.launch {
            provider.getCategories()
        }

        coroutineScope.launch {
            connectivity.networkStatus.collect {
                println("XXXXX CONNECTION STATUS: $it")
            }
        }
    }

    private val slotNavigation = SlotNavigation<SlotConfig>()

    override val childSlot: Value<ChildSlot<*, CategoryList.SlotChild>> = childSlot(
        source = slotNavigation,
        serializer = SlotConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChildSlot
    )

    override fun navigateBreweryDetails(id: String) {
        onNavigateCategory(id)
    }

    private fun createChildSlot(
        config: SlotConfig,
        componentContext: ComponentContext
    ): CategoryList.SlotChild {
        return when (config) {
            is SlotConfig.ExpensDetails -> {
                CategoryList.SlotChild.BreweryInfo(
                    BreweryInfoComponent(
                        componentContext = componentContext,
                        onDismiss = slotNavigation::dismiss
                    )
                )
            }
        }
    }
}

fun LifecycleOwner.coroutineScope(
    context: CoroutineContext = Dispatchers.Main.immediate,
): CoroutineScope {
    val scope = CoroutineScope(context + SupervisorJob())
    lifecycle.doOnDestroy(scope::cancel)

    return scope
}

inline fun <reified T : Any> DecomposeKoinScopeComponent.get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    return if (this is DecomposeKoinScopeComponent) {
        scope!!.get(qualifier, parameters)
    } else {
        getKoin().get(qualifier, parameters)
    }
}

/**
 * Lazy inject instance from Koin
 * @param qualifier
 * @param mode - LazyThreadSafetyMode
 * @param parameters
 */
inline fun <reified T : Any> DecomposeKoinScopeComponent.inject(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = KoinPlatformTools.defaultLazyMode(),
    noinline parameters: ParametersDefinition? = null,
): Lazy<T> =
    lazy(mode) { get<T>(qualifier, parameters) }
