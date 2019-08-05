package com.github.martingaston.application.http;

import java.nio.charset.StandardCharsets;

public class Body {
    private final String body;
    private final Integer length;

    private Body(String body) {
        this.body = body;
        this.length = body.getBytes(StandardCharsets.UTF_8).length;
    }

    public static Body from(String body) {
        return new Body(body);
    }

    public int contentLength() {
        return this.length;
    }

    @Override
    public String toString() {
        return this.body;
    }

    @Override
    public boolean equals(Object obj) {
        Body uri = (Body) obj;
        return getClass() == obj.getClass() && this.body.equals(uri.body);
    }
}
