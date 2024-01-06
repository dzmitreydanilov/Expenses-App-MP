package com.ddanilov.beerlover.breweries

import com.ddanilov.beerlover.DecomposeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CategoriesListViewModel(
    private val repository: BreweriesListRepository
) : DecomposeViewModel() {

    private val _state = MutableStateFlow<CategoriesListState>(CategoriesListState.Initial)
    val breweriesState = _state.asStateFlow()

    init {
        getBreweriesList()
    }

    fun getBreweriesList() {
        coroutineScope.launch {
            repository.getBreweriesList(itemsOnPage = 50)
                .onStart { _state.emit(CategoriesListState.Loading(_state.value.categories)) }
                .collect { result ->
                    result.onSuccess { breweries ->
                        _state.emit(CategoriesListState.Loaded(breweries))
                    }.onFailure {
                        _state.emit(
                            CategoriesListState.Error(
                                error = it,
                                isErrorPopup = _state.value.isErrorPopup,
                                categories = _state.value.categories
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
                        _state.emit(CategoriesListState.Loaded(breweries))
                    }.onFailure {
                        _state.emit(
                            CategoriesListState.Error(
                                error = it,
                                isErrorPopup = true,
                                categories = _state.value.categories
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
                    CategoriesListState.Tick(
                        tickValue = counter.toString(),
                        categories = _state.value.categories,
                        isErrorPopup = _state.value.isErrorPopup
                    )
                )
                println("XXXX Live Update value $counter")
                counter++
            }
        }
    }
}
