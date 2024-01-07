package com.ddanilov.beerlover.decompose.favorite

import com.arkivanov.decompose.ComponentContext
import com.ddanilov.beerlover.AppComponentContext
import com.ddanilov.beerlover.decompose.expenseslist.createScopeForCurrentLifecycle
import org.koin.core.scope.Scope

class FavoriteComponent(
    componentContext: AppComponentContext
) : Favorite,
    AppComponentContext by componentContext {

    override val scope: Scope = createScopeForCurrentLifecycle(this)
}
