package example.micronaut

import io.micronaut.core.annotation.Creator
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import javax.validation.constraints.NotBlank

@Introspected // <1>
class FruitCommand {

    @NonNull
    @NotBlank // <2>
    final String name

    @Nullable // <3>
    final String description

    FruitCommand(@NonNull String name) {
        this(name, null)
    }

    @Creator
    FruitCommand(@NonNull String name, @Nullable String description) {
        this.name = name
        this.description = description
    }
}
