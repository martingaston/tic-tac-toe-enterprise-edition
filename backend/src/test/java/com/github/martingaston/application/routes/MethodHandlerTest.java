package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A MethodHandler class")
class MethodHandlerTest {
    private MethodHandler methodHandler;
    private Handler handler = (req, res) -> res;

    @BeforeEach
    void init() {
        methodHandler = new MethodHandler();
    }

    @DisplayName("Returns true if a method exists")
    @Test
    void methodExists() {
        methodHandler.addMethod(Verbs.POST, handler);
        assertThat(methodHandler.isValidMethod(Verbs.POST)).isTrue();
        assertThat(methodHandler.isValidMethod(Verbs.GET)).isFalse();
    }

    @DisplayName("Can output valid methods sorted alphabetically")
    @Test
    void knowsValidMethods() {
        methodHandler.addMethod(Verbs.POST, handler);
        methodHandler.addMethod(Verbs.HEAD, handler);
        methodHandler.addMethod(Verbs.GET, handler);

        assertThat(methodHandler.valid()).isEqualTo("GET, HEAD, POST");
    }

    @DisplayName("Does not overwrite existing methods once added")
    @Test
    void doesNotOverwrite() {
        var request = new Request(new RequestLine(Verbs.POST, URI.from("/refactor_echo_body"), Version.V1POINT1), new Headers(), Body.from("It is a truth universally acknowledged..."));
        methodHandler.addMethod(Verbs.POST, (req, res) -> res.body(Body.from("ABC")));
        methodHandler.addMethod(Verbs.POST, (req, res) -> res.body(Body.from("DEF")));

        Response.Options response = new Response.Options(Status.OK);
        Body body = methodHandler.get(Verbs.POST).handle(request, response).build().body();
        assertThat(body).isEqualTo(Body.from("ABC"));
    }
}