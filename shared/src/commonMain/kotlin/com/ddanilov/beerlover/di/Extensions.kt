package com.ddanilov.beerlover.di

import com.expenses.core.di.AbstractFeatureApi
import org.koin.core.Koin

fun <F : AbstractFeatureApi> Koin.getFeatureApiManager(feature: F): FeatureApiManager<F> {
    val scopeManager = getScopeOrNull(feature.qualifier.value)?.get<FeatureApiManager<F>>()
    if (scopeManager != null) return scopeManager
    return FeatureApiManager(this, feature)
}