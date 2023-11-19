package com.ddanilov.beerlover.breweries

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.ddanilov.beerlover.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

class BreweriesViewModel(
    private val repository: BreweriesListRepository
) : ViewModel(), InstanceKeeper.Instance {

    private val _state = MutableStateFlow<BreweriesState>(BreweriesState.Initial)
    val breweriesState = _state.asStateFlow()

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
                        println("XXXX getBreweriesList VM")
                    }.onFailure {
                        _state.emit(BreweriesState.Error(it))
                    }
                }
        }
    }

    override fun onDestroy() {
        println("XXXX onDestroy VM")
        onCleared()
    }
}
