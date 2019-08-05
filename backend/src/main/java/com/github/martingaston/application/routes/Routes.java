package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

public class Routes {
    private PathHandler paths;

    public Routes() {
        paths = new PathHandler();
    }

    private Handler emptyResponse = (req, res) -> res;

    public void get(URI uri) {
        get(uri, emptyResponse);
    }

    public void get(URI uri, Handler handler) {
        addRoute(Verbs.GET, uri, handler);
        addRoute(Verbs.HEAD, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void post (URI uri) {
        post(uri, emptyResponse);
    }

    public void post(URI uri, Handler handler) {
        addRoute(Verbs.POST, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void put(URI uri) {
        put(uri, emptyResponse);
    }

    public void put(URI uri, Handler handler) {
        addRoute(Verbs.PUT, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void head(URI uri) {
        head(uri, emptyResponse);
    }

    public void head(URI uri, Handler handler) {
        addRoute(Verbs.HEAD, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    private void addRoute(Verbs method, URI uri, Handler handler) {
        paths.addPath(uri, new MethodHandler()).addMethod(method, handler);
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
