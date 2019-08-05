package com.github.martingaston.application.http;

import com.github.martingaston.application.routes.Routes;

public class Router {
    private Routes routes;

    public Router(Routes routes) {
        this.routes = routes;
    }

    public Response respond(Request request) {
        Response.Options response = createDefaultResponse();

        if (invalidRequest(request)) {
            return sendBadRequestResponse(response);
        }

        if (invalidPath(request, routes)) {
            return sendNotFoundResponse(response);
        }

        if (optionsRequest(request)) {
            return sendOptionsResponse(request, response, routes);
        }

        if (invalidMethod(request, routes)) {
            return sendMethodNotFoundResponse(request, response, routes);
        }

        return handleValidRequest(request, response, routes);
    }

    private Response.Options createDefaultResponse() {
        return new Response.Options(Status.OK)
                    .addHeader("Connection", "close");
    }

    private static Response handleValidRequest(Request request, Response.Options response, Routes routes) {
        routes.handler(request).handle(request, response);

        if (headRequest(request)) {
            response.body(Body.from(""));
        }

        return response.build();
    }

    private boolean invalidRequest(Request request) {
        return request.method() == Verbs.INVALID || !request.hasHeader("Host") || request.protocol() == Version.INVALID;
    }

    private Response sendBadRequestResponse(Response.Options response) {
        response.status(Status.BAD_REQUEST);
        return response.build();
    }

    private static Response sendMethodNotFoundResponse(Request request, Response.Options response, Routes routes) {
        response.status(Status.METHOD_NOT_ALLOWED);
        response.addHeader("Allow", routes.validAtPath(request));
        return response.build();
    }

    private static Response sendNotFoundResponse(Response.Options response) {
        response.status(Status.NOT_FOUND);
        return response.build();
    }

    private static Response sendOptionsResponse(Request request, Response.Options response, Routes routes) {
        response.addHeader("Allow", routes.validAtPath(request));
        response.body(Body.from(""));
        return response.build();
    }

    private static boolean headRequest(Request request) {
        return request.method() == Verbs.HEAD;
    }

    private static boolean invalidMethod(Request request, Routes routes) {
        return !routes.isValidMethod(request.method(), request.uri());
    }

    private static boolean optionsRequest(Request request) {
        return request.method() == Verbs.OPTIONS;
    }

    private static boolean invalidPath(Request request, Routes routes) {
        return !routes.isValidPath(request.uri());
    }
}
