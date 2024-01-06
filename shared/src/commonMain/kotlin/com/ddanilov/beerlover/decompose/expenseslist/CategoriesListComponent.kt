package com.ddanilov.beerlover.decompose.expenseslist

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.ddanilov.beerlover.decompose.expense.BreweryInfoComponent
import com.ddanilov.beerlover.decompose.home.SlotConfig
import com.expenses.category.CategoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import org.koin.core.component.getScopeId
import org.koin.core.component.getScopeName
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeCallback
import org.koin.mp.KoinPlatform.getKoin
import kotlin.coroutines.CoroutineContext

class CategoriesListComponent(
    componentContext: ComponentContext,
    private val onNavigateCategory: (String) -> Unit
) : CategoryList, ComponentContext by componentContext, KoinComponent {

    val scope = createScopeForCurrentLifecycle(this)

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

fun LifecycleOwner.createScopeForCurrentLifecycle(owner: LifecycleOwner): Scope {
    val scope = getKoin().createScope(getScopeId(), getScopeName(), this)

    scope.registerCallback(object : ScopeCallback {
        override fun onScopeClose(scope: Scope) {
            println("XXXXX onScopeClose")
            (owner as KoinScopeComponent).onCloseScope()
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

interface KoinScopeComponent {

    //TODO Make scope nullable with var?

    /**
     * Current Scope in use by the component
     */
    val scope: Scope

    /**
     * Called before closing a scope, on onDestroy
     */
    fun onCloseScope() {}
}
