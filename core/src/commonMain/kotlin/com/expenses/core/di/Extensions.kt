package com.expenses.core.di

import com.ddanilov.beerlover.di.FeatureApiManager
import org.koin.core.Koin

fun <F : AbstractFeatureApi> Koin.getFeatureApiManager(feature: F): FeatureApiManager<F> {
    val scopeManager = getScopeOrNull(feature.qualifier.value)?.get<FeatureApiManager<F>>()
    if (scopeManager != null) return scopeManager
    return FeatureApiManager(this, feature)
}