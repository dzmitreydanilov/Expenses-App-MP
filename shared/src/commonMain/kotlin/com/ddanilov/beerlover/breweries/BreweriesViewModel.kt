package com.ddanilov.beerlover.breweries

import com.ddanilov.beerlover.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("BreweriesViewModelDelegate")
class BreweriesViewModel(
    private val repository: BreweriesListRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BreweriesState>(BreweriesState.Initial)
    val state = _state.asStateFlow()

    init {
        getBreweriesList()
    }

    private fun getBreweriesList() {
        scope.launch {
            _state.emit(BreweriesState.Loading)
            repository.getBreweriesList(itemsOnPage = 50)
                .onStart { _state.emit(BreweriesState.Loading) }.collect { result ->
                    result.onSuccess { breweries ->
                        _state.emit(BreweriesState.Loaded(breweries))
                    }.onFailure {
                        _state.emit(BreweriesState.Error(it))
                    }
                }
        }
    }
}
