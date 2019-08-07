package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.URI;
import com.github.martingaston.application.http.Verbs;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Routes class")
class RoutesTest {
    private Routes routes;
    private Handler handler = (req, res) -> res;

    @BeforeEach
    void newRoute() {
        routes = new Routes();
    }

    @DisplayName("Can register a route")
    @Test
    void canAddPostRoute() {
        routes.post("/refactor_echo_body", handler);
        assertThat(routes.isValid(Verbs.POST, URI.from("/refactor_echo_body"))).isTrue();
    }

    @DisplayName("Can register multiple routes to one path")
    @Test
    void canAddMultipleRoutes() {
        routes.post("/refactor_echo_body", handler);
        routes.get("/refactor_echo_body", handler);
        assertThat(routes.isValid(Verbs.GET, URI.from("/refactor_echo_body"))).isTrue();
        assertThat(routes.isValid(Verbs.POST, URI.from("/refactor_echo_body"))).isTrue();
    }

    @DisplayName("Knows an invalid path")
    @Test
    void knowsAnInvalidPath() {
        routes.post("/correct", handler);
        assertThat(routes.isValid(Verbs.POST, URI.from("/wrong"))).isFalse();
    }

    @DisplayName("Knows an invalid method")
    @Test
    void knowsAnInvalidMethod() {
        routes.get("/get_content", handler);
        assertThat(routes.isValid(Verbs.POST, URI.from("/get_content"))).isFalse();
    }
}