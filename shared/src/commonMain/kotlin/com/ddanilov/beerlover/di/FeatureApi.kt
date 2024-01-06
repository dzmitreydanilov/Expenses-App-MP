package com.ddanilov.beerlover.di

import com.expenses.core.di.AbstractFeatureApi
import com.expenses.core.di.ApiDSL
import com.expenses.core.di.ApiDefinition
import com.expenses.core.di.FeatureApi
import org.koin.core.component.getScopeName
import org.koin.core.qualifier.Qualifier

/**
 * The base class for declaring an API feature within the API module, specifically interfaces
 * definitions, the implementations of which can be obtained from this feature.
 */
abstract class FeatureApi : AbstractFeatureApi() {

    final override val qualifier: Qualifier = getScopeName()

    override val isLibrary: Boolean = false

    final override var dependencies: List<AbstractFeatureApi> = emptyList()

    protected abstract fun ApiDSL.definitions()

    fun getApiDefinitions(): List<ApiDefinition> =
        ApiDSL().apply { definitions() }.definitions
}

abstract class LibraryApi : FeatureApi() {

    override val isLibrary: Boolean = true
}