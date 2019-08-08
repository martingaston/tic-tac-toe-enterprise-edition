package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

public class Routes {
    private PathHandler paths;

    public Routes() {
        paths = new PathHandler();
    }

    private Handler emptyResponse = (req, res) -> res;

    public void get(String uri) {
        get(uri, emptyResponse);
    }

    public void get(String uri, Handler handler) {
        addRoute(Verbs.GET, uri, handler);
        addRoute(Verbs.HEAD, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void post (String uri) {
        post(uri, emptyResponse);
    }

    public void post(String uri, Handler handler) {
        addRoute(Verbs.POST, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void put(String uri) {
        put(uri, emptyResponse);
    }

    public void put(String uri, Handler handler) {
        addRoute(Verbs.PUT, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void head(String uri) {
        head(uri, emptyResponse);
    }

    public void head(String uri, Handler handler) {
        addRoute(Verbs.HEAD, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    private void addRoute(Verbs method, String uri, Handler handler) {
        paths.addPath(URI.from(uri), new MethodHandler()).addMethod(method, handler);
    }

    public boolean isValid(Verbs method, URI uri) {
        return paths.isValidPath(uri) && paths.get(uri).isValidMethod(method);
    }

    public boolean isValidPath(URI uri) {
        return paths.isValidPath(uri);
    }

    public boolean isValidMethod(Verbs method, URI uri) {
        return paths.get(uri).isValidMethod(method);
    }

    public String validAtPath(Request request) {
        return paths.get(request.uri()).valid();
    }

    public Handler handler(Request request) {
        return paths.get(request.uri()).get(request.method());
    }
}
