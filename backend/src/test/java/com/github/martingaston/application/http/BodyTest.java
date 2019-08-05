package com.github.martingaston.application.http;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Body class")
class BodyTest {
    @DisplayName("Is equal to itself")
    @Test
    void isEqual() {
        assertThat(Body.from("some body")).isEqualTo(Body.from("some body"));
        assertThat(Body.from("some body")).isNotEqualTo(Body.from("some other body"));
    }

    @DisplayName("Can return its contents")
    @Test
    void returnsContents() {
        assertThat(Body.from("my new body").toString()).isEqualTo("my new body");
    }

    @DisplayName("Can return its length")
    @Test
    void returnsLength() {
        assertThat(Body.from("can anybody find me").contentLength()).isEqualTo(19);
    }
}
