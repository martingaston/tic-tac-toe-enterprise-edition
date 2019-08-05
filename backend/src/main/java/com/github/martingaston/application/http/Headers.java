package com.github.martingaston.application.http;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    private final Map<String, String> headers;

    public Headers() {
        this.headers = new HashMap<>();
    }

    public boolean contains(String header) {
        return this.headers.containsKey(header);
    }

    public <T> void add(String header, T value) {
        this.headers.putIfAbsent(header, value.toString());
    }

    public String get(String header) {
        return this.headers.getOrDefault(header, "");
    }

    @Override
    public String toString() {
        final String separator = "\r\n";
        StringBuilder formattedHeaders = new StringBuilder();
        headers.forEach((header, value) -> formattedHeaders.append(header).append(": ").append(value).append(separator));
        return formattedHeaders.toString();
    }
}
