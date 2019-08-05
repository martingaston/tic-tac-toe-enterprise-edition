package com.github.martingaston.application.http;

public class Request {
    private final RequestLine requestLine;
    private final Headers headers;
    private final Body body;

    public Request(RequestLine requestLine, Headers headers, Body body) {
        this.requestLine = requestLine;
        this.headers = headers;
        this.body = body;
    }

    public Verbs method() {
        return this.requestLine.method();
    }

    public URI uri() {
        return this.requestLine.uri();
    }

    public Version protocol() {
        return this.requestLine.version();
    }

    public boolean hasHeader(String header) {
        return this.headers.contains(header);
    }

    public String getHeader(String header) {
        return this.headers.get(header);
    }

    public Body body() {
        return this.body;
    }
}
