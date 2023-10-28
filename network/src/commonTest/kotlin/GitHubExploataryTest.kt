import com.danilov.network.response.handler.DefaultHttpResponseHandler
import com.danilov.network.Response
import com.danilov.network.createHttpClient
import com.danilov.network.createJson
import com.danilov.network.response.validator.DefaultResponseValidator
import configs.TestGithubSearchApiService
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class GitHubApiServiceExploratoryTest {

    private lateinit var apiService: TestGithubSearchApiService

    @BeforeTest
    fun init() {
        val json = createJson()
        val httpClient = createHttpClient(
            engine = getHttpEngine(),
            json = json,
            networkLoggingEnabled = false,
            responseValidator = DefaultResponseValidator()
        )
        apiService = TestGithubSearchApiService(
            httpClient, DefaultHttpResponseHandler()
        )
    }

    @Test
    fun `Correctly fetches tetris repositories`(): Unit = runBlocking {
        val expected = "react-tetris"
        val response = apiService.fetchRepositories()
        val result = (response as Response.Success).body.items.first().name

        assertEquals(expected, result)
    }
}
