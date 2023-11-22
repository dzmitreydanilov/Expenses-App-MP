package com.ddanilov.beerlover.network

import com.danilov.network.RestResponse
import com.danilov.network.base.ApiService
import com.danilov.network.buildUrl
import com.danilov.network.response.handler.HttpResponseHandler
import com.ddanilov.beerlover.models.Brewery
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesIgnore
import io.ktor.client.HttpClient

class BreweriesListApiService(
    httpClient: HttpClient,
    responseHandle: HttpResponseHandler
) : ApiService(httpClient, responseHandle) {

    @NativeCoroutinesIgnore
    suspend fun getBreweryList(): RestResponse<List<Brewery>> {
        return get(
            urlBuilder = {
                buildUrl(
                    baseUrl = "https://api.openbrewerydb.org/v1/breweries",
                    queryParams = mapOf("per_page" to  "30"),
                )
            }
        )
    }

    @NativeCoroutinesIgnore
    suspend fun getBreweryById(id: String): RestResponse<Brewery> {
        return get(
            urlBuilder = {
                buildUrl(
                    baseUrl = "https://api.openbrewerydb.org/v1/breweries/b54b16e1-ac3b-4bff-a11f-f7ae9ddc27e0",
                )
            }
        )
    }
}
