package com.ddanilov.beerlover.breweries

import com.danilov.network.RestResponse
import com.ddanilov.beerlover.models.Brewery
import com.ddanilov.beerlover.network.BreweriesListApiService
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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
        }
            .onStart { delay(500) }
            .toResult()
            .catch { emit(Result.failure(it)) }
    }

    fun getBreweriesListWithError(itemsOnPage: Int): Flow<Result<List<Brewery>>> {
        return flow {
            val breweries = apiService.getBreweryList()
            emit(breweries)
            throw Exception("TEST EXCEPTION")
        }
            .onStart { delay(500) }
            .toResult()
            .catch { emit(Result.failure(it)) }
    }

    @NativeCoroutinesIgnore
    suspend fun getBreweryById(id: String): Result<Brewery> {
        return try {
            apiService.getBreweryById(id).toResult()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private inline fun <reified T> Flow<RestResponse<T>>.toResult(): Flow<Result<T>> {
    return map { response ->
        if (response is RestResponse.Success) {
            Result.success(response.body)
        } else {
            val error = (response as RestResponse.Error)
            Result.failure(Throwable(error.errorBody))
        }
    }
}

private inline fun <reified T> RestResponse<T>.toResult(): Result<T> {
    return if (this is RestResponse.Success) {
        Result.success(body)
    } else {
        val error = (this as RestResponse.Error)
        Result.failure(Throwable(error.errorBody))
    }
}
