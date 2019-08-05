package com.github.martingaston.application.http;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Response class")
class ResponseTest {
    @Nested
    @DisplayName("Can build a response")
    class canBuildResponse {
        @Test
        void builds200Response() {
            Response response = new Response.Options(Status.OK)
                    .addHeader("Connection", "Close")
                    .body(Body.from("Hello World"))
                    .build();

            assertThat(response.version()).isEqualTo(Version.V1POINT1);
            assertThat(response.status()).isEqualTo(Status.OK);
            assertThat(response.body()).isEqualTo(Body.from("Hello World"));
            assertThat(response.headers().get("Content-Length")).isEqualTo("11");
        }

        @Test
        void builds404Response() {
            Response response = new Response.Options(Status.NOT_FOUND).build();

            assertThat(response.status()).isEqualTo(Status.NOT_FOUND);
        }
    }
}