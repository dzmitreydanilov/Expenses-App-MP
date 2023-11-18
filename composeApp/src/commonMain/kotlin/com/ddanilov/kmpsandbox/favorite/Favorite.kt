package com.ddanilov.kmpsandbox.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ddanilov.beerlover.decompose.favorite.Favorite

@Composable
fun FavoriteScreen(component: Favorite, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(15.dp)) {
            Text("Favorite breweries screen")

            Text("Work in progress")
        }
    }
}
