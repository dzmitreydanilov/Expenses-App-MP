package com.ddanilov.beerlover.decompose.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.ddanilov.beerlover.decompose.home.HomeComponent

interface Root {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Home(val component: HomeComponent) : Child
    }
}
