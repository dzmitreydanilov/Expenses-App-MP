package com.expenses.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.ddanilov.beerlover.decompose.home.Home
import com.ddanilov.beerlover.decompose.home.HomeComponent
import com.expenses.app.breweries.BreweriesList
import com.expenses.app.favorite.FavoriteScreen

@Composable
fun HomeScreen(component: HomeComponent, modifier: Modifier = Modifier) {
    val childStack by component.stack.subscribeAsState()
    val activeComponent = childStack.active.instance

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
                modifier = Modifier.weight(1f)
            ) {
                when (val child = it.instance) {
                    is Home.Child.Breweries -> BreweriesList(component = child.component)
                    is Home.Child.Favorites -> FavoriteScreen(component = child.component)
                    is Home.Child.BreweryDetails -> {}
                }
            }
            BottomNavigation(activeComponent = activeComponent, component = component)
        }
    }
}

@Composable
private fun BottomNavigation(
    activeComponent: Home.Child,
    component: Home,
    modifier: Modifier = Modifier
) {
    BottomAppBar(modifier = modifier) {
        NavigationBarItem(
            selected = activeComponent is Home.Child.Breweries,
            onClick = { component.onTabClick(Home.Tab.ExpensesHome) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Breweries",
                )
            }
        )

        NavigationBarItem(
            selected = activeComponent is Home.Child.Favorites,
            onClick = { component.onTabClick(Home.Tab.Favorite) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Favorite",
                )
            }
        )
    }
}

