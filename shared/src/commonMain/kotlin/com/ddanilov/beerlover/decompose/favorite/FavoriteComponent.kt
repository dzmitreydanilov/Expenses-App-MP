package com.ddanilov.beerlover.decompose.favorite

import com.expenses.core.decompose.AppComponentContext
import com.expenses.core.decompose.createKoinScopeForCurrentLifecycle
import org.koin.core.scope.Scope

class FavoriteComponent(
    componentContext: AppComponentContext
) : Favorite, AppComponentContext by componentContext {

    override val scope: Scope = createKoinScopeForCurrentLifecycle(this)
}
