package com.ddanilov.beerlover.network

import com.danilov.network.Response
import com.danilov.network.base.ApiService
import com.danilov.network.buildUrl
import com.danilov.network.response.handler.HttpResponseHandler
import com.ddanilov.beerlover.models.Brewery
import io.ktor.client.HttpClient

class BreweriesListApi(
    httpClient: HttpClient,
    responseHandle: HttpResponseHandler
) : ApiService(httpClient, responseHandle) {

    suspend fun getBreweryList(): Response<Brewery> {
        return get(
            urlBuilder = {
                buildUrl(
                    baseUrl = "https://api.openbrewerydb.org/v1/",
                    path = "breweries?per_page=30"
                )
            }
        )
    }
}
