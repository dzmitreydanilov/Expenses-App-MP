package configs

import com.danilov.network.ErrorResponse
import com.danilov.network.Response
import com.danilov.network.base.ApiService
import com.danilov.network.buildUrl
import com.danilov.network.response.handler.HttpResponseHandler
import io.ktor.client.HttpClient

internal class TestGithubSearchApiService(
    httpClient: HttpClient,
    responseHandler: HttpResponseHandler
) : ApiService(
    httpClient,
    responseHandler
) {

    suspend fun fetchRepositories(): Response<ResponseWrapperSchema, ErrorResponse> {
        return get(
            urlBuilder = {
                buildUrl(
                    baseUrl = "https://api.github.com/search/repositories?q=tetris&sort=stars&order=desc",
                    path = "search/repositories",
                    queryParams = mapOf(
                        "q" to "tetris",
                        "sort" to "stars",
                        "order" to "desc"
                    )
                )
            }
        )
    }
}
