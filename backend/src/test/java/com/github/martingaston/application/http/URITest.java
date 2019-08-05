package com.github.martingaston.application.http;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A URI class")
class URITest {
    @DisplayName("Is equal to itself")
    @Test
    void isEqual() {
        assertThat(URI.from("/")).isEqualTo(URI.from("/"));
        assertThat(URI.from("/")).isNotEqualTo(URI.from("/home"));
    }

    @DisplayName("Can return its contents")
    @Test
    void returnsContents() {
        assertThat(URI.from("/home").toString()).isEqualTo("/home");
    }
}
