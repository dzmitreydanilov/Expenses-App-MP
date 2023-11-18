package com.ddanilov.beerlover.breweries

import com.danilov.network.RestResponse
import com.ddanilov.beerlover.models.Brewery
import com.ddanilov.beerlover.network.BreweriesListApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class BreweriesListRepository(
    private val apiService: BreweriesListApiService
) {

    fun getBreweriesList(itemsOnPage: Int): Flow<Result<List<Brewery>>> {
        return flow {
            val breweries = apiService.getBreweryList()
            emit(breweries)
        }.onStart { delay(500) }
            .map { response ->
                if (response is RestResponse.Success) {
                    Result.success(response.body)
                } else {
                    val error = (response as RestResponse.Error)
                    Result.failure(Throwable(error.errorBody))
                }
            }
    }
}

