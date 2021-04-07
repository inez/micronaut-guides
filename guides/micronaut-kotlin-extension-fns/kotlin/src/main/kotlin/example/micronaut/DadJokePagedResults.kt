package example.micronaut

import io.micronaut.core.annotation.Introspected

@Introspected // <1>
data class DadJokePagedResults(
    val current_page: Int,
    val limit: Int,
    val previous_page: Int,
    val next_page: Int,
    val total_jokes: Int,
    val total_pages: Int,
    val results: List<DadJoke>
)
