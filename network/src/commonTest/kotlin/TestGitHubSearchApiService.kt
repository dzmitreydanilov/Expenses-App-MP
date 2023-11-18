import com.goncalossilva.resources.Resource
import com.danilov.network.RestResponse
import com.danilov.network.createHttpClient
import com.danilov.network.createJson
import com.danilov.network.response.handler.DefaultHttpResponseHandler
import com.danilov.network.response.validator.DefaultResponseValidator
import configs.TestGithubSearchApiService
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GitHubApiMockTest {

    private lateinit var json: Json

    @BeforeTest
    fun init() {
        json = createJson()
    }

    @Test
    fun `correctly fetches tetris repositories`(): Unit = runBlocking {
        val engine = createMockEngine(
            resourcePath = "src/commonTest/resources/SuccessResponseReactTetris.json"
        )
        val httpClient = createHttpClient(
            engine = engine,
            json = json,
            networkLoggingEnabled = false,
            responseValidator = DefaultResponseValidator()
        )
        val apiService = TestGithubSearchApiService(
            httpClient, DefaultHttpResponseHandler()
        )
        val expected = "react-tetris"
        val results = apiService.fetchRepositories()
        val result = (results as RestResponse.Success).body.items.first().name
        assertEquals(expected, result)
    }

    @Test
    fun `receive error Unauthorized error code`(): Unit = runBlocking {
        val expected = 401
        val engine = createMockEngine(
            statusCode = HttpStatusCode.Unauthorized
        )
        val httpClient = createHttpClient(
            engine = engine,
            json = json,
            networkLoggingEnabled = false,
            responseValidator = DefaultResponseValidator()
        )
        val apiService = TestGithubSearchApiService(
            httpClient, DefaultHttpResponseHandler()
        )
        val response = apiService.fetchRepositories()
        val result = ((response as RestResponse.Error) as RestResponse.Error.HttpError).code

        assertEquals(expected, result)
    }

}

private fun createMockEngine(
    resourcePath: String = "",
    statusCode: HttpStatusCode = HttpStatusCode.OK
): HttpClientEngine {
    return MockEngine {
        respond(
            content = if (resourcePath.isNotEmpty()) Resource(resourcePath).readText() else "",
            status = statusCode,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
    }
}
