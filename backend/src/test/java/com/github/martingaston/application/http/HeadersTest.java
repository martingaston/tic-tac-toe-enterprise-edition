package com.github.martingaston.application.http;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Headers class")
class HeadersTest {
    private Headers headers;

    @BeforeEach
    void initHeaders() {
        headers = new Headers();
    }

    @DisplayName("Can addPath and retrieve headers")
    @Test
    void addAndRetrieve() {
        assertThat(headers.contains("Connection")).isFalse();
        headers.add("Connection", "close");
        assertThat(headers.contains("Connection")).isTrue();
        assertThat(headers.get("Connection")).isEqualTo("close");
    }

    @DisplayName("Will use toString to store values as Strings")
    @Test
    void valueIsString() {
        headers.add("Content-Length", 1234);
        assertThat(headers.get("Content-Length")).isEqualTo("1234");
    }

    @DisplayName("Does not overwrite existing headers")
    @Test
    void doesNotOverwrite() {
        headers.add("Content-Length", 456);
        headers.add("Content-Length", 123);

        assertThat(headers.get("Content-Length")).isEqualTo("456");
    }

    @DisplayName("Can return headers as a properly formatted string")
    @Test
    void returnsAsString() {
        headers.add("Connection", "close");
        headers.add("Content-Length", 42);
        assertThat(headers.toString()).isEqualTo("Connection: close\r\nContent-Length: 42\r\n");
    }
}