package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.URI;

import java.util.concurrent.ConcurrentHashMap;

class PathHandler {
    private ConcurrentHashMap<URI, MethodHandler> paths;

    PathHandler() {
        paths = new ConcurrentHashMap<>();
    }

    public MethodHandler addPath(URI uri, MethodHandler methods) {
        paths.putIfAbsent(uri, methods);
        return get(uri);
    }

    public boolean isValidPath(URI uri) {
        return paths.containsKey(uri);
    }

    public MethodHandler get(URI uri) {
        return paths.get(uri);
    }
}