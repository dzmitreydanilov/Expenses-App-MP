package com.ddanilov.beerlover.di

import com.expenses.core.di.ApiDefinition
import org.koin.core.definition.Kind
import org.koin.core.qualifier.Qualifier
import kotlin.reflect.KClass

data class ApiDefinition(
    val kind: Kind,
    val qualifier: Qualifier?,
    val type: KClass<*>
)

class ApiDSL {

    val definitions = mutableListOf<ApiDefinition>()

    inline fun <reified T> scoped(qualifier: Qualifier? = null) {
        definitions.add(ApiDefinition(Kind.Scoped, qualifier, T::class))
    }

    inline fun <reified T> factory(qualifier: Qualifier? = null) {
        definitions.add(ApiDefinition(Kind.Factory, qualifier, T::class))
    }
}