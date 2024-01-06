package com.expenses.core.di

import org.koin.core.qualifier.Qualifier

abstract class AbstractFeatureApi {

    abstract val qualifier: Qualifier

    abstract val isLibrary: Boolean

    abstract val dependencies: List<AbstractFeatureApi>
}