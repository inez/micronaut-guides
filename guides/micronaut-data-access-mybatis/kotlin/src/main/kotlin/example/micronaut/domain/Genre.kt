package example.micronaut.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import io.micronaut.core.annotation.Introspected

@Introspected
data class Genre(var name: String) {

    var id: Long? = null

    @JsonIgnore
    var books: Set<Book> = mutableSetOf()

    override fun toString() = "Genre{id=$id, name='$name', books=$books}"
}
