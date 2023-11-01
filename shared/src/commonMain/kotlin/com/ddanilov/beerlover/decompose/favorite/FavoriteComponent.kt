package com.ddanilov.beerlover.decompose.favorite

import com.arkivanov.decompose.ComponentContext

class FavoriteComponent(
    componentContext: ComponentContext
) : Favorite,
    ComponentContext by componentContext {
}
