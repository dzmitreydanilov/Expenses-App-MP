package com.ddanilov.beerlover.breweries.details

import com.ddanilov.beerlover.breweries.BreweriesListRepository
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class BreweryDetailsViewModel(
    private val repository: BreweriesListRepository
) : KMMViewModel() {

    private val _state =
        MutableStateFlow<BreweryDetailsState>(viewModelScope, BreweryDetailsState.Initial)

    @NativeCoroutinesState
    val state = _state.asStateFlow()

    @NativeCoroutines
    suspend fun getBrewery(id: String) {
        repository.getBreweryById(id)
            .onSuccess { _state.emit(BreweryDetailsState.Loaded(it)) }
            .onFailure { _state.emit(BreweryDetailsState.Error(it)) }
    }

    fun collectLiveUpdate() {
        viewModelScope.coroutineScope.launch {
            var count = 0
            while (true) {
                delay(500)
                _state.emit(BreweryDetailsState.Tick(_state.value.brewery))
                println("XXXX LiveUpdate BreweryDetailsViewModel $count")
                count++
            }
        }
    }
}
