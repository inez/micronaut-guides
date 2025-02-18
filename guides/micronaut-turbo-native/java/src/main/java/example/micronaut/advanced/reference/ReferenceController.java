package example.micronaut.advanced.reference;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import example.micronaut.model.ViewModel;
import io.micronaut.views.View;

@Controller("/reference")
class ReferenceController {

    @View("reference")
    @Get(produces = {MediaType.TEXT_HTML}, consumes = {MediaType.TEXT_HTML})
    ViewModel index() {
        return new ViewModel("Reference", "index");
    }

    @View("turbo-drive")
    @Get(value = "/turbo-drive", produces = {MediaType.TEXT_HTML}, consumes = {MediaType.TEXT_HTML})
    ViewModel turboDrive() {
        return new ViewModel("Turbo Drive");
    }

    @View("turbo-frames")
    @Get(value = "/turbo-frames", produces = {MediaType.TEXT_HTML}, consumes = {MediaType.TEXT_HTML})
    ViewModel turboFrames() {
        return new ViewModel("Turbo Frames");
    }

    @View("turbo-streams")
    @Get(value = "/turbo-streams", produces = {MediaType.TEXT_HTML}, consumes = {MediaType.TEXT_HTML})
    ViewModel turboStreams() {
        return new ViewModel("Turbo Streams");
    }

    @View("turbo-native")
    @Get(value = "/turbo-native", produces = {MediaType.TEXT_HTML}, consumes = {MediaType.TEXT_HTML})
    ViewModel turboNative() {
        return new ViewModel("Turbo Native");
    }
}