package com.github.martingaston.application.http;

public enum Status {
    OK("200 OK"),
    MOVED_PERMANENTLY("301 Moved Permanently"),
    BAD_REQUEST("400 Bad Request"),
    NOT_FOUND("404 Not Found"),
    METHOD_NOT_ALLOWED("405 Method Not Allowed");

    private String response;

    Status(String response) {
        this.response = response;
    }

    public String response() {
        return response;
    }
}
