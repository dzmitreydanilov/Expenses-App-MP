package com.expenses.core.di

import com.ddanilov.beerlover.di.ImplDSL
import org.koin.core.component.getScopeName
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

/**
 * Base class for declaring API feature implementations within the 'impl' module,
 * as well as [dependencies] required for creating these implementations.
 */
abstract class FeatureImpl(val api: FeatureApi? = null) : AbstractFeatureApi() {

    final override val qualifier: Qualifier = api?.getScopeName() ?: this.getScopeName()

    override val isLibrary: Boolean = api?.isLibrary ?: false

    override val dependencies: List<AbstractFeatureApi> = emptyList()

    protected abstract fun ImplDSL.definitions()

    fun createModule(): Module {
        api?.dependencies = dependencies

        return Module(false).apply {
            scope(qualifier) { definitions() }
        }
    }
}

abstract class LibraryImpl(api: LibraryApi) : FeatureImpl(api) {

    override val isLibrary: Boolean = api.isLibrary
}