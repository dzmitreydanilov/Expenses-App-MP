package com.ddanilov.beerlover.network

import com.danilov.network.RestResponse
import com.danilov.network.base.ApiService
import com.danilov.network.buildUrl
import com.danilov.network.response.handler.HttpResponseHandler
import com.ddanilov.beerlover.models.Brewery
import io.ktor.client.HttpClient

class BreweriesListApiService(
    httpClient: HttpClient,
    responseHandle: HttpResponseHandler
) : ApiService(httpClient, responseHandle) {

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
}
