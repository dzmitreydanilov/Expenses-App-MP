package com.expenses.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.unit.dp
import com.ddanilov.beerlover.decompose.expenseslist.CategoryList

@Composable
fun BreweriesList(
    component: CategoryList,
    modifier: Modifier = Modifier
) {
//    val state by component.state.collectAsState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
//        items(state.breweries) {
//            Brewery(it)
//        }
    }
}

@Composable
fun Brewery(brewery: com.ddanilov.beerlover.models.Brewery, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .focusProperties { canFocus = false }) {
        Text(text = "Name: ${brewery.name}")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Address: ${brewery.address1}")
    }
}
