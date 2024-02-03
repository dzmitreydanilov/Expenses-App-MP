package com.expenses.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ddanilov.beerlover.decompose.categories.CategoryList

@Composable
fun CategoriesList(
    component: CategoryList,
    modifier: Modifier = Modifier
) {
//    val state by component.state.collectAsState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        item {
            Text(text = "Hello")
        }
    }
}
