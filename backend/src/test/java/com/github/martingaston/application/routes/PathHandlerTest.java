package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.URI;
import com.github.martingaston.application.routes.MethodHandler;
import com.github.martingaston.application.routes.PathHandler;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A PathHandler class")
class PathHandlerTest {
    private PathHandler pathHandler;

    @BeforeEach
    void init() {
        pathHandler = new PathHandler();
    }

    @DisplayName("Returns true if a path exists")
    @Test
    void pathExists() {
        pathHandler.addPath(URI.from("/test_path"), new MethodHandler());
        assertThat(pathHandler.isValidPath(URI.from("/test_path"))).isTrue();
        assertThat(pathHandler.isValidPath(URI.from("/wrong_path"))).isFalse();
    }

    @DisplayName("Does not overwrite existing paths once added")
    @Test
    void doesNotOverwrite() {
        MethodHandler methodHandler = new MethodHandler();
        pathHandler.addPath(URI.from("/duplicate_path"), methodHandler);
        pathHandler.addPath(URI.from("/duplicate_path"), new MethodHandler());

        assertThat(pathHandler.get(URI.from("/duplicate_path"))).isEqualTo(methodHandler);
    }
}
