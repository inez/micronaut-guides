package example.micronaut

import jakarta.inject.Singleton
import groovy.transform.CompileStatic

@CompileStatic
@Singleton // <1>
class AuthoritiesFetcherService implements AuthoritiesFetcher {

    private final UserRoleGormService userRoleGormService

    AuthoritiesFetcherService(UserRoleGormService userRoleGormService) { // <2>
        this.userRoleGormService = userRoleGormService
    }

    @Override
    List<String> findAuthoritiesByUsername(String username) {
        userRoleGormService.findAllAuthoritiesByUsername(username)
    }
}
