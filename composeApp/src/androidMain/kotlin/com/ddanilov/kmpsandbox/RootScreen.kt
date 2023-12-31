package com.ddanilov.kmpsandbox

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.isFront
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.ddanilov.beerlover.decompose.root.Root
import com.ddanilov.beerlover.decompose.root.RootComponent
import com.ddanilov.kmpsandbox.breweries.BreweriesList

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.stack.subscribeAsState()
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            Children(
                stack = childStack,
                modifier = Modifier.weight(1f),
                stackAnimation { _, _, direction ->
                    if (direction.isFront) {
                        slide() + fade()
                    } else {
                        scale(frontFactor = 1F, backFactor = 0.7F) + fade()
                    }
                }
            ) {
                when (val child = it.instance) {
                    is Root.Child.Home -> HomeScreen(component = child.component)
                }
            }
        }
    }
}
