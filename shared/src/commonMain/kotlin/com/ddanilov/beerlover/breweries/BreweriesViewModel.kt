package com.ddanilov.beerlover.breweries

import com.ddanilov.beerlover.DecomposeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BreweriesViewModel(
    private val repository: BreweriesListRepository
) : DecomposeViewModel() {

    private val _state = MutableStateFlow<BreweriesState>(BreweriesState.Initial)
    val breweriesState = _state.asStateFlow()

    init {
        getBreweriesList()
    }

    fun getBreweriesList() {
        coroutineScope.launch {
            repository.getBreweriesList(itemsOnPage = 50)
                .onStart { _state.emit(BreweriesState.Loading(_state.value.breweries)) }
                .collect { result ->
                    result.onSuccess { breweries ->
                        _state.emit(BreweriesState.Loaded(breweries))
                    }.onFailure {
                        _state.emit(
                            BreweriesState.Error(
                                error = it,
                                isErrorPopup = _state.value.isErrorPopup,
                                breweries = _state.value.breweries
                            )
                        )
                    }
                }
        }
    }

    fun getBreweriesListWithError() {
        coroutineScope.launch {
            repository.getBreweriesListWithError(itemsOnPage = 50)
                .collect { result ->
                    result.onSuccess { breweries ->
                        _state.emit(BreweriesState.Loaded(breweries))
                    }.onFailure {
                        _state.emit(
                            BreweriesState.Error(
                                error = it,
                                isErrorPopup = true,
                                breweries = _state.value.breweries
                            )
                        )
                    }
                }
        }
    }

    fun collectLiveUpdates() {
        var counter = 0
        coroutineScope.launch {
            while (true) {
                delay(2_000)
                _state.emit(
                    BreweriesState.Tick(
                        tickValue = counter.toString(),
                        breweries = _state.value.breweries,
                        isErrorPopup = _state.value.isErrorPopup
                    )
                )
                println("XXXX Live Update value $counter")
                counter++
            }
        }
    }
}
