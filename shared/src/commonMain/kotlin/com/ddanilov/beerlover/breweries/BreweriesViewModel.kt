package com.ddanilov.beerlover.breweries

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.ddanilov.beerlover.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.time.TimeInMillis
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName
import kotlin.random.Random

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
                .onStart { _state.emit(BreweriesState.Loading) }
                .collect { result ->
                    result.onSuccess { breweries ->
                        _state.emit(BreweriesState.Loaded(breweries))
                        println("XXXX getBreweriesList VM")
                    }.onFailure {
                        _state.emit(BreweriesState.Error(it))
                    }
                }
        }
    }

    fun liveUpdate() {
        var counter = 0
        scope.launch {
            while (true) {
                delay(2_000)
                val previous = _state.value
                println("XXXX Tick $counter")
                _state.emit(BreweriesState.Tick(counter.toString(), previous.breweries))
                counter++
            }
        }

    }

    override fun onDestroy() {
        println("XXXX onDestroy VM")
        onCleared()
    }
}
