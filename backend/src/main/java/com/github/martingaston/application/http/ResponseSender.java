package com.github.martingaston.application.http;

public class ResponseSender {
    private ResponseSender() {
    }

    public static String from(Response response) {
        String separator = "\r\n";
        return response.version().name + " " + response.status().response() + separator
                + response.headers().toString() + separator
                + response.body();
    }
}
