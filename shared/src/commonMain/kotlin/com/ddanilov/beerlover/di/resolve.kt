package com.ddanilov.beerlover.di

import org.koin.core.scope.Scope

inline fun <reified R> Scope.resolve(
    constructor: () -> R,
): R = constructor()

inline fun <reified R, reified T1> Scope.resolve(
    constructor: (T1) -> R,
): R = constructor(get())

inline fun <reified R, reified T1, reified T2> Scope.resolve(
    constructor: (T1, T2) -> R,
): R = constructor(get(), get())