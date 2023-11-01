package com.ddanilov.composeapp.breweries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.unit.dp
import com.ddanilov.beerlover.decompose.breweries.BreweryList
import com.ddanilov.beerlover.decompose.breweries.BreweryListComponent

@Composable
fun BreweriesList(
    component: BreweryList,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        items(breweryList) {
            Brewery(it)
        }
    }
}

data class Brewery(
    val name: String,
    val address: String
)

@Composable
fun Brewery(brewery: Brewery, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth().focusProperties { canFocus = false }) {
        Text(text = "Name: ${brewery.name}")
        Spacer(modifier = Modifier.width(10.dp))
        Text("Address: ${brewery.address}")
    }
}

val breweryList = listOf(
    Brewery("Brewery 1", "123 Main St"),
    Brewery("Brewery 2", "456 Oak Ave"),
    Brewery("Brewery 3", "789 Pine Blvd"),
    Brewery("Brewery 4", "101 Elm Dr"),
    Brewery("Brewery 5", "202 Cedar Ln"),
    Brewery("Brewery 6", "555 Maple Rd"),
    Brewery("Brewery 7", "777 Birch St"),
    Brewery("Brewery 8", "888 Walnut Ave"),
    Brewery("Brewery 9", "999 Spruce Blvd"),
    Brewery("Brewery 10", "333 Pine Ln"),
    Brewery("Brewery 11", "444 Oak Dr"),
    Brewery("Brewery 12", "666 Elm Rd"),
    Brewery("Brewery 13", "777 Cedar Blvd"),
    Brewery("Brewery 14", "888 Maple Dr"),
    Brewery("Brewery 15", "999 Birch Ln"),
    Brewery("Brewery 16", "111 Oak St"),
    Brewery("Brewery 17", "222 Pine Ave"),
    Brewery("Brewery 18", "333 Elm Blvd"),
    Brewery("Brewery 19", "444 Cedar Dr"),
    Brewery("Brewery 20", "555 Maple Ln"),
    Brewery("Brewery 21", "666 Birch Rd"),
    Brewery("Brewery 22", "777 Walnut Blvd"),
    Brewery("Brewery 23", "888 Spruce Dr"),
    Brewery("Brewery 24", "999 Pine Ln"),
    Brewery("Brewery 25", "123 Cedar Dr"),
    Brewery("Brewery 26", "456 Birch Rd"),
    Brewery("Brewery 27", "789 Walnut Blvd"),
    Brewery("Brewery 28", "101 Spruce Dr"),
    Brewery("Brewery 29", "202 Pine Ln"),
    Brewery("Brewery 30", "555 Elm St"),
    Brewery("Brewery 31", "777 Cedar Ave"),
    Brewery("Brewery 32", "888 Maple Blvd"),
    Brewery("Brewery 33", "999 Birch Dr"),
    Brewery("Brewery 34", "333 Walnut Rd"),
    Brewery("Brewery 35", "444 Spruce Blvd"),
    Brewery("Brewery 36", "666 Pine St"),
    Brewery("Brewery 37", "777 Elm Ave"),
    Brewery("Brewery 38", "888 Cedar Blvd"),
    Brewery("Brewery 39", "999 Maple Dr"),
    Brewery("Brewery 40", "123 Birch Ln"),
    Brewery("Brewery 41", "456 Walnut St"),
    Brewery("Brewery 42", "789 Spruce Ave"),
    Brewery("Brewery 43", "101 Pine Blvd"),
    Brewery("Brewery 44", "202 Elm Dr"),
    Brewery("Brewery 45", "555 Cedar Ln"),
    Brewery("Brewery 46", "777 Maple Rd"),
    Brewery("Brewery 47", "888 Birch St"),
    Brewery("Brewery 48", "999 Walnut Ave"),
    Brewery("Brewery 49", "333 Spruce Blvd"),
    Brewery("Brewery 50", "444 Pine Ln"),
    Brewery("Brewery 51", "666 Oak Dr"),
    Brewery("Brewery 52", "777 Elm Rd"),
    Brewery("Brewery 53", "888 Cedar Blvd"),
    Brewery("Brewery 54", "999 Maple Dr"),
    Brewery("Brewery 55", "123 Birch Ln"),
    Brewery("Brewery 56", "456 Walnut St"),
    Brewery("Brewery 57", "789 Spruce Ave"),
    Brewery("Brewery 58", "101 Pine Blvd"),
    Brewery("Brewery 59", "202 Elm Dr"),
    Brewery("Brewery 60", "555 Cedar Ln"),
    Brewery("Brewery 61", "777 Maple Rd"),
    Brewery("Brewery 62", "888 Birch St"),
    Brewery("Brewery 63", "999 Walnut Ave"),
    Brewery("Brewery 64", "333 Spruce Blvd"),
    Brewery("Brewery 65", "444 Pine Ln"),
    Brewery("Brewery 66", "666 Oak Dr"),
    Brewery("Brewery 67", "777 Elm Rd"),
    Brewery("Brewery 68", "888 Cedar Blvd"),
    Brewery("Brewery 69", "999 Maple Dr"),
    Brewery("Brewery 70", "123 Birch Ln"),
    Brewery("Brewery 71", "456 Walnut St"),
    Brewery("Brewery 72", "789 Spruce Ave"),
    Brewery("Brewery 73", "101 Pine Blvd"),
    Brewery("Brewery 74", "202 Elm Dr"),
    Brewery("Brewery 75", "555 Cedar Ln"),
    Brewery("Brewery 76", "777 Maple Rd"),
    Brewery("Brewery 77", "888 Birch St"),
    Brewery("Brewery 78", "999 Walnut Ave"),
    Brewery("Brewery 79", "333 Spruce Blvd"),
    Brewery("Brewery 80", "444 Pine Ln"),
    Brewery("Brewery 81", "666 Oak Dr"),
    Brewery("Brewery 82", "777 Elm Rd"),
    Brewery("Brewery 83", "888 Cedar Blvd"),
    Brewery("Brewery 84", "999 Maple Dr"),
    Brewery("Brewery 85", "123 Birch Ln"),
    Brewery("Brewery 86", "456 Walnut St"),
    Brewery("Brewery 87", "789 Spruce Ave"),
    Brewery("Brewery 88", "101 Pine Blvd"),
    Brewery("Brewery 89", "202 Elm Dr"),
    Brewery("Brewery 90", "555 Cedar Ln"),
    Brewery("Brewery 91", "777 Maple Rd"),
    Brewery("Brewery 92", "888 Birch St"),
    Brewery("Brewery 93", "999 Walnut Ave"),
    Brewery("Brewery 94", "333 Spruce Blvd"),
    Brewery("Brewery 95", "444 Pine Ln"),
    Brewery("Brewery 96", "666 Oak Dr"),
    Brewery("Brewery 97", "777 Elm Rd"),
    Brewery("Brewery 98", "888 Cedar Blvd"),
    Brewery("Brewery 99", "999 Maple Dr"),
    Brewery("Brewery 100", "123 Birch Ln")
)
