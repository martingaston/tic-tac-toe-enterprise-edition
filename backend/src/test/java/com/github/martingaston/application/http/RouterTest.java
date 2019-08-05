package com.github.martingaston.application.http;

import com.github.martingaston.application.routes.Routes;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Router class")
class RouterTest {
    static private Router router;
    static private Headers headers;

    @BeforeAll
    static void initAll() {
        Routes routes = new Routes();
        routes.post(URI.from("/echo_body"), (req, res) -> res.send(Body.from(req.body().toString())));

        routes.head(URI.from("/get_with_body"));

        routes.get(URI.from("/simple_get"));

        routes.get(URI.from("/method_options"));

        routes.get(URI.from("/method_options2"));

        routes.post(URI.from("/method_options2"));

        routes.put(URI.from("/method_options2"));

        routes.get(URI.from("/redirect"), (req, res) -> {
            URI location = URI.from("http://127.0.0.1:5000/simple_get");
            return res.redirect(Status.MOVED_PERMANENTLY, location);
        });

        router = new Router(routes);

        headers = new Headers();
        headers.add("Host", "localhost:5000");
    }

    @DisplayName("With a valid POST request")
    @Nested
    class canRouteValidPOST {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.POST, URI.from("/echo_body"), Version.V1POINT1), headers, Body.from("It is a truth universally acknowledged..."));
            response = router.respond(request);
        }

        @DisplayName("Returns a 200 status code")
        @Test
        void returns200StatusCode() {
            assertThat(response.status()).isEqualTo(Status.OK);
        }

        @DisplayName("Echoes the Request body")
        @Test
        void echoesRequestBody() {
            assertThat(response.body()).isEqualTo(Body.from("It is a truth universally acknowledged..."));
        }
    }

    @DisplayName("With a invalid method request")
    @Nested
    class canRouteInvalidMethod {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.GET, URI.from("/get_with_body"), Version.V1POINT1), headers, Body.from(""));
            response = router.respond(request);
        }

        @DisplayName("Returns a 405 status code")
        @Test
        void returns405StatusCode() {
            assertThat(response.status()).isEqualTo(Status.METHOD_NOT_ALLOWED);
        }
    }

    @DisplayName("With a HEAD request on a path that exists as a GET")
    @Nested
    class respondsToHeadForGetPaths {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.HEAD, URI.from("/simple_get"), Version.V1POINT1), headers, Body.from(""));
            response = router.respond(request);
        }

        @DisplayName("Returns a 200 status code")
        @Test
        void returnsA200StatusCode() {
            assertThat(response.status()).isEqualTo(Status.OK);
        }

        @DisplayName("Response has no body")
        @Test
        void returnsAnEmptyBody() {
            assertThat(response.body()).isEqualTo(Body.from(""));
        }

        @DisplayName("Response has a Content-Length header")
        @Test
        void returnsContentLengthHeader() {
            assertThat(response.headers().get("Content-Length")).isEqualTo("0");
        }
    }

    @DisplayName("With an OPTIONS request on a single GET path")
    @Nested
    class respondsToOptionsOnOneVerbPath {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.OPTIONS, URI.from("/method_options"), Version.V1POINT1), headers, Body.from(""));
            response = router.respond(request);
        }

        @DisplayName("Returns a 200 status code")
        @Test
        void returnsA200StatusCode() {
            assertThat(response.status()).isEqualTo(Status.OK);
        }

        @DisplayName("Response has no body")
        @Test
        void returnsAnEmptyBody() {
            assertThat(response.body()).isEqualTo(Body.from(""));
        }

        @DisplayName("Response has an Allow header of GET, HEAD, OPTIONS")
        @Test
        void returnsContentLengthHeader() {
            assertThat(response.headers().get("Allow")).isEqualTo("GET, HEAD, OPTIONS");
        }
    }

    @DisplayName("With an OPTIONS request on GET, POST and PUT path")
    @Nested
    class respondsToOptionsOnThreeVerbPath {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.OPTIONS, URI.from("/method_options2"), Version.V1POINT1), headers, Body.from(""));
            response = router.respond(request);
        }

        @DisplayName("Returns a 200 status code")
        @Test
        void returnsA200StatusCode() {
            assertThat(response.status()).isEqualTo(Status.OK);
        }

        @DisplayName("Response has no body")
        @Test
        void returnsAnEmptyBody() {
            assertThat(response.body()).isEqualTo(Body.from(""));
        }

        @DisplayName("Response has an Allow header of GET, HEAD, OPTIONS")
        @Test
        void returnsContentLengthHeader() {
            assertThat(response.headers().get("Allow")).isEqualTo("GET, HEAD, OPTIONS, POST, PUT");
        }
    }

    @DisplayName("With an invalid path request")
    @Nested
    class canRouteInvalidPath {
        @DisplayName("Returns a 404 status code")
        @Test
        void returns404StatusCode() {
            Request request = new Request(new RequestLine(Verbs.POST, URI.from("/totally_invalid_path"), Version.V1POINT1), headers, Body.from("It is a truth universally acknowledged..."));
            Response response = router.respond(request);
            assertThat(response.status()).isEqualTo(Status.NOT_FOUND);
        }
    }

    @DisplayName("Can handle invalid requests")
    @Nested
    class canRouteBadRequests {
        @DisplayName("With an invalid method")
        @Test void withInvalidMethod() {
           Request request = new Request(new RequestLine(Verbs.INVALID, URI.from("/echo_body"), Version.V1POINT1), headers, Body.from(""));
           Response response = router.respond(request);

           assertThat(response.status()).isEqualTo(Status.BAD_REQUEST);
        }

        @DisplayName("Without a Host header")
        @Test void withoutHostHeader() {
            Request request = new Request(new RequestLine(Verbs.POST, URI.from("/echo_body"), Version.V1POINT1), new Headers(), Body.from(""));
            Response response = router.respond(request);

            assertThat(response.status()).isEqualTo(Status.BAD_REQUEST);
        }

        @DisplayName("With an invalid HTTP version")
        @Test void withInvalidVersion() {
            Request request = new Request(new RequestLine(Verbs.POST, URI.from("/echo_body"), Version.INVALID), headers, Body.from(""));
            Response response = router.respond(request);

            assertThat(response.status()).isEqualTo(Status.BAD_REQUEST);
        }
    }

    @DisplayName("With a GET request on a redirected route")
    @Nested
    class canRedirectRequest {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.GET, URI.from("/redirect"), Version.V1POINT1), headers, Body.from(""));
            response = router.respond(request);
        }

        @DisplayName("Returns a 301 status code")
        @Test
        void returns301StatusCode() {
            assertThat(response.status()).isEqualTo(Status.MOVED_PERMANENTLY);
        }
    }
}
