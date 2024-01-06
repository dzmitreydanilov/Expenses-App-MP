package com.ddanilov.beerlover.di

import com.expenses.core.di.AbstractFeatureApi
import org.koin.core.qualifier.Qualifier

abstract class AbstractFeatureApi {

    abstract val qualifier: Qualifier

    abstract val isLibrary: Boolean

    abstract val dependencies: List<AbstractFeatureApi>
}