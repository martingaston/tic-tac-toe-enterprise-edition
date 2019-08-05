package com.github.martingaston.application.http;

import com.github.martingaston.application.Client;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Request class")
class RequestTest {
    @Nested
    @DisplayName("With a valid POST request containing a body and two headers")
    class postRequestNoHeaders {
        private ByteArrayInputStream input = new ByteArrayInputStream("POST /echo_body HTTP/1.1\r\nHost: localhost:5000\r\nContent-Length: 9\r\n\r\nsome body".getBytes());
        private ByteArrayOutputStream output = new ByteArrayOutputStream();
        private Client client = new Client(input, output);
        private Request request;

        @BeforeEach
        void init() throws IOException {
            request = RequestParser.from(client);
        }

        @DisplayName("Will return a POST method")
        @Test
        void hasPostMethod() {
            assertThat(request.method()).isEqualTo(Verbs.POST);
        }

        @DisplayName("Will return a URI of /echo_body")
        @Test
        void hasEchoBodyURI() {
            assertThat(request.uri()).isEqualTo(URI.from("/echo_body"));
        }

        @DisplayName("Will return a HTTP method of HTTP/1.1")
        @Test
        void hasHttpMethod() {
            assertThat(request.protocol()).isEqualTo(Version.V1POINT1);
        }

        @DisplayName("Will contain headers")
        @Test
        void containsHeaders() {
            assertThat(request.hasHeader("Content-Length")).isTrue();
            assertThat(request.hasHeader("Host")).isTrue();
        }

        @DisplayName("Will return the headers")
        @Test
        void hasContentLengthHeader() {
            assertThat(request.getHeader("Content-Length")).isEqualTo("9");
            assertThat(request.getHeader("Host")).isEqualTo("localhost:5000");
        }

        @DisplayName("Will return a body")
        @Test
        void returnsBody() {
            assertThat(request.body()).isEqualTo(Body.from("some body"));
        }
    }

    @Nested
    @DisplayName("Will gracefully handle a null request")
    class handlesInvalidRequests {
        private ByteArrayInputStream input = new ByteArrayInputStream("".getBytes());
        private ByteArrayOutputStream output = new ByteArrayOutputStream();
        private Client client = new Client(input, output);
        private Request request;

        @BeforeEach
        void init() throws IOException {
            request = RequestParser.from(client);
        }

        @DisplayName("Will return an invalid method")
        @Test
        void hasInvalidMethod() {
            assertThat(request.method()).isEqualTo(Verbs.INVALID);
        }

        @DisplayName("Will return an invalid version")
        @Test
        void hasInvalidVersion() {
            assertThat(request.protocol()).isEqualTo(Version.INVALID);
        }
    }
}