package com.ddanilov.kmpsandbox

import androidx.compose.ui.window.ComposeUIViewController
import com.ddanilov.beerlover.decompose.expenseslist.BreweryList
import com.ddanilov.beerlover.decompose.favorite.Favorite
import com.expenses.app.breweries.BreweriesList
import com.expenses.app.favorite.FavoriteScreen
import com.expenses.app.theme.AppTheme
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
