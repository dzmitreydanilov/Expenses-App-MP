package com.ddanilov.beerlover.breweries.favorite

import com.ddanilov.beerlover.DecomposeViewModel
import com.ddanilov.beerlover.breweries.BreweriesListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteBreweryViewModel(
    private val repository: BreweriesListRepository
) : DecomposeViewModel() {

    private val _state = MutableStateFlow<FavoriteBreweryState>(FavoriteBreweryState.Initial)

    val favoriteBreweryState = _state.asStateFlow()

    suspend fun fetchFavoriteBreweryById(): FavoriteBreweryState {
        var result: FavoriteBreweryState = FavoriteBreweryState.Initial
        repository.getBreweryById(id = "b54b16e1-ac3b-4bff-a11f-f7ae9ddc27e0")
            .onSuccess {
                result = FavoriteBreweryState.Loaded(it)
            }
            .onFailure {
                result = FavoriteBreweryState.Error(it)
            }

        return result
    }
}
