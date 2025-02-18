package example.micronaut

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@MicronautTest // <1>
class GithubControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient // <2>

    @Test
    fun verifyGithubReleasesCanBeFetchedWithLowLevelHttpClient() {
        //when:
        val request: HttpRequest<Any> = HttpRequest.GET("/github/releases-lowlevel")
        val rsp = client.toBlocking().exchange(request, // <3>
            Argument.listOf(GithubRelease::class.java)) // <4>

        //then: 'the endpoint can be accessed'
        assertEquals(HttpStatus.OK, rsp.status) // <5>
        assertNotNull(rsp.body()) // <6>

        //when:
        val releases = rsp.body()

        //then:
        assertNotNull(releases)
        val regex = Regex("Micronaut( Framework)? [0-9].[0-9].[0-9]([0-9])?( (RC|M)[0-9])?")
        for (release in releases) {
            assertTrue(regex.matches(release.name))
        }
    }

    @Test
    fun verifyGithubReleasesCanBeFetchedWithCompileTimeAutoGeneratedAtClient() {
        //when:
        val request: HttpRequest<Any> = HttpRequest.GET("/github/releases-lowlevel")
        val githubReleases = client.toBlocking().retrieve(request, Argument.listOf(GithubRelease::class.java)) // <7>

        //then:
        val regex = Regex("Micronaut( Framework)? [0-9].[0-9].[0-9]([0-9])?( (RC|M)[0-9])?")
        for (release in githubReleases) {
            assertTrue(regex.matches(release.name))
        }
    }
}
