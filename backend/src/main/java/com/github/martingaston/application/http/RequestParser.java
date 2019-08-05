package com.github.martingaston.application.http;

import com.github.martingaston.application.Client;

import java.io.IOException;

public class RequestParser {
    private RequestParser() {

    }

    public static Request from(Client client) throws IOException {
        RequestLine requestLine = parseRequestLine(client);
        Headers headers = parseHeaders(client);
        Body body = parseBody(client);

        return new Request(requestLine, headers, body);
    }

    private static RequestLine parseRequestLine(Client client) throws IOException {
        String[] separatedRequestLine = client.receive().split(" ");

        if (separatedRequestLine.length != 3) {
            return new RequestLine(Verbs.INVALID, URI.from(""), Version.INVALID);
        }

        return new RequestLine(
                Verbs.from(separatedRequestLine[0]),
                URI.from(separatedRequestLine[1]),
                Version.from(separatedRequestLine[2])
        );
    }

    private static Headers parseHeaders(Client client) throws IOException {
        Headers headers = new Headers();
        String currentHeader;

        currentHeader = client.receive();
        while (!currentHeader.equals("")) {
            String[] splitHeader = currentHeader.trim().split(": ");
            headers.add(splitHeader[0], splitHeader[1]);
            currentHeader = client.receive();
        }

        return headers;
    }

    private static Body parseBody(Client client) throws IOException {
        return Body.from(client.receiveBody());
    }
}
