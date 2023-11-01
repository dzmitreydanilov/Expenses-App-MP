package com.ddanilov.composeapp

import androidx.compose.ui.window.ComposeUIViewController
import com.ddanilov.beerlover.decompose.breweries.BreweryList
import com.ddanilov.beerlover.decompose.favorite.Favorite
import com.ddanilov.composeapp.breweries.BreweriesList
import com.ddanilov.composeapp.favorite.FavoriteScreen
import com.ddanilov.composeapp.theme.AppTheme
import platform.UIKit.UIViewController

fun BreweriesViewController(component: BreweryList): UIViewController {
    return ComposeUIViewController {
        AppTheme {
            BreweriesList(component)
        }
    }
}

fun FavoritesViewController(component: Favorite): UIViewController {
    return ComposeUIViewController {
        AppTheme {
            FavoriteScreen(component)
        }
    }
}
