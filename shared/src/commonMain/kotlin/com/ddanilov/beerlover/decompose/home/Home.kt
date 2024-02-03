package com.ddanilov.beerlover.decompose.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.categories.CategoryList
import com.ddanilov.beerlover.decompose.favorite.Favorite

interface Home {

    val stack: Value<ChildStack<*, Child>>

    fun onTabClick(tabs: Tab)

    enum class Tab {
        ExpensesHome,
        Favorite
    }

    fun onBackClicked()

    sealed class Child {
        class CategoriesList(val component: CategoryList) : Child()
        class Favorites(val component: Favorite) : Child()
        class CategoryInfo(val id: String) : Child()
    }
}
