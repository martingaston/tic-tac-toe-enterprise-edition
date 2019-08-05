package com.github.martingaston.application.http;

public class RequestLine {
    private Verbs method;
    private URI uri;
    private Version version;

    public RequestLine(Verbs method, URI uri, Version version) {
        this.method = method;
        this.uri = uri;
        this.version = version;
    }

    public Verbs method() {
        return this.method;
    }

    public URI uri() {
        return this.uri;
    }

    public Version version() {
        return this.version;
    }
}
